package com.mayco.listadepaises.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mayco.listadepaises.R
import com.mayco.listadepaises.model.Pais
import com.mayco.listadepaises.utils.Constants.ALEMANHA
import com.mayco.listadepaises.utils.Constants.BRASIL
import kotlin.properties.Delegates

class CountryAndapter : RecyclerView.Adapter<CountryAndapter.CountryViewHolder>(), Filterable {


    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Pais) {
            val nameCountry = itemView.findViewById<TextView>(R.id.countryountry)
            val imageView = itemView.findViewById<ImageView>(R.id.imageCountry)

            with(itemView) {
                nameCountry.text = item.name
                if (item.name == ALEMANHA) {
                    imageView.background = context.resources.getDrawable(R.drawable.alemanha)
                }
                if (item.name == BRASIL) {
                    imageView.background = context.resources.getDrawable(R.drawable.brasil)
                }
            }
        }
    }

    var items: List<Pais> by Delegates.observable(emptyList()) { _, old, new -> if (old != new) notifyDataSetChanged() }

    var paisList: List<Pais> by Delegates.observable(emptyList()) { _, old, new ->
        if (old != new) notifyDataSetChanged()
    }
    var filterList = ArrayList<Pais>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val atual = items[position]
        holder.bind(item = atual)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    paisList as ArrayList<Pais>
                } else {
                    val resultList = ArrayList<Pais>()
                    for (row in paisList) {
                        if (row.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                try {
                    items = results?.values as List<Pais>
                    notifyDataSetChanged()
                } catch (e: Exception) {
                    items = paisList
                    notifyDataSetChanged()
                }
            }
        }
    }
}





