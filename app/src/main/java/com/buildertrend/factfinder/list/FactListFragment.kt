package com.buildertrend.factfinder.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.EditText
import com.buildertrend.factfinder.DatabaseManager
import com.buildertrend.factfinder.FactAdapter
import com.buildertrend.factfinder.MainActivity
import com.buildertrend.factfinder.R
import com.buildertrend.factfinder.database.Fact
import com.buildertrend.factfinder.extensions.hide
import com.buildertrend.factfinder.extensions.show
import com.buildertrend.factfinder.random
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_fact_list.*
import javax.inject.Inject

class FactListFragment : Fragment() {

    @Inject internal lateinit var databaseManager: DatabaseManager
    @Inject internal lateinit var adapter: FactAdapter

    private val compositeDisposable = CompositeDisposable()
    private var shouldSelectFunFact = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val mainActivity = activity as? MainActivity
        mainActivity?.getFragmentComponent()?.inject(this)
        super.onCreate(savedInstanceState)

        mainActivity?.setFabClickListener(View.OnClickListener { showAddFactDialog() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        compositeDisposable.add(
            databaseManager.getAllFacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.facts = it

                    if (it.isEmpty()) {
                        noContentGroup.show()
                        contentGroup.hide()
                    } else {
                        noContentGroup.hide()
                        contentGroup.show()
                        if (shouldSelectFunFact) {
                            selectRandomFunFact()
                        }
                    }
                    shouldSelectFunFact = false
                }
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun showAddFactDialog() {
        with(requireContext()) {
            val editText = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Add a fun fact")
                .setPositiveButton("ok") { _, _ -> databaseManager.insertFact(editText.text.toString()) }
                .setNegativeButton("cancel") { dialog, _ -> dialog.dismiss() }
                .setView(editText)
                .show()
        }
    }

    private fun showFactDialog(fact: Fact) {
        AlertDialog.Builder(requireContext())
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

    companion object {
        fun newInstance() = FactListFragment()
    }
}
