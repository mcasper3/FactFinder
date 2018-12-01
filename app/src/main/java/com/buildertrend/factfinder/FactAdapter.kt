package com.buildertrend.factfinder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.buildertrend.factfinder.database.Fact
import javax.inject.Inject

class FactAdapter @Inject constructor() : RecyclerView.Adapter<FactViewHolder>() {

    var facts: List<Fact> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_fact, parent, false) as TextView,
            {} // TODO
        )
    }

    override fun getItemCount() = facts.size

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) = holder.bind(facts[position])
}
