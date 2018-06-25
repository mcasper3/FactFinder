package com.buildertrend.factfinder

import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.buildertrend.factfinder.database.Fact

class FactViewHolder(
    private val view: TextView,
    private val onItemClick: (fact: Fact) -> Unit
) : RecyclerView.ViewHolder(view) {

    fun bind(fact: Fact) {
        view.text = fact.information
        view.setOnClickListener { onItemClick(fact) }
    }
}
