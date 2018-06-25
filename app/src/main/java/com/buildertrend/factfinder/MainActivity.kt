package com.buildertrend.factfinder

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val facts = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_select_fact.setOnClickListener {  }

        btn_add_fact.setOnClickListener {
            val editText = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Add a fun fact")
                .setPositiveButton("ok", { _, _ ->
                    // TODO store the fact in the list and in the DB
                })
                .setNegativeButton("cancel", { dialog, _ -> dialog.dismiss() })
                .setView(editText)
                .show()
        }
    }
}
