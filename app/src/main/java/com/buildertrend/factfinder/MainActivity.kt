package com.buildertrend.factfinder

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.EditText
import com.buildertrend.factfinder.database.Fact
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val databaseManager = DatabaseManager(this)
    private val adapter = FactAdapter(this::showFactDialog)

    private var disposable: Disposable? = null
    private var shouldSelectFunFact = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        btn_select_fact.setOnClickListener { selectRandomFunFact() }

        btn_add_fact.setOnClickListener { showAddFactDialog() }

        handleIntent(intent)

        disposable = databaseManager.getAllFacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter.facts = it

                if (it.isEmpty()) {
                    no_content_group.visibility = View.VISIBLE
                    content_group.visibility = View.GONE
                } else {
                    no_content_group.visibility = View.GONE
                    content_group.visibility = View.VISIBLE
                    if (shouldSelectFunFact) {
                        selectRandomFunFact()
                    }
                }
                shouldSelectFunFact = false
            }
    }

    private fun showAddFactDialog() {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("Add a fun fact")
            .setPositiveButton("ok", { _, _ -> databaseManager.insertFact(editText.text.toString()) })
            .setNegativeButton("cancel", { dialog, _ -> dialog.dismiss() })
            .setView(editText)
            .show()
    }

    private fun showFactDialog(fact: Fact) {
        AlertDialog.Builder(this)
            .setTitle(R.string.your_fun_fact_is_label)
            .setMessage(fact.information)
            .setPositiveButton(R.string.ok) { _, _ -> databaseManager.deleteFact(fact) }
            .setNegativeButton(R.string.cancel) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun selectRandomFunFact() {
        val position = (0..adapter.itemCount).random()
        showFactDialog(adapter.facts[position])
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_INSERT -> showAddFactDialog()
            Intent.ACTION_GET_CONTENT -> {
                if (adapter.itemCount > 0) {
                    selectRandomFunFact()
                } else {
                    shouldSelectFunFact = true
                }
            }
        }
    }
}
