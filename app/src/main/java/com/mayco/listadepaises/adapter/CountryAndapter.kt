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
import com.mayco.listadepaises.utils.Constants.CANADA
import com.mayco.listadepaises.utils.Constants.ESTADOSUNIDOS
import com.mayco.listadepaises.utils.Constants.JAPAO
import com.mayco.listadepaises.utils.Constants.PORTUGAL
import kotlin.properties.Delegates

class CountryAndapter : RecyclerView.Adapter<CountryAndapter.CountryViewHolder>(), Filterable {


    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Pais) {
            val nameCountry = itemView.findViewById<TextView>(R.id.countryountry)
            val imageView = itemView.findViewById<ImageView>(R.id.imageCountry)
            val countryPais = itemView.findViewById<TextView>(R.id.discricao)

            with(itemView) {
                nameCountry.text = item.name
                if (item.name == ALEMANHA) {
                   // imageView.background = context.resources.getDrawable(R.drawable.alemanha)
                    imageView.setImageResource(R.drawable.alemanha)
                    countryPais.setText(R.string.alemanha)

                }
                if (item.name == BRASIL) {
                   // imageView.background = context.resources.getDrawable(R.drawable.brasil)
                    imageView.setImageResource(R.drawable.brasil)
                    countryPais.setText(R.string.brasil)
                }
                if (item.name == CANADA){
                    //imageView.background = context.resources.getDrawable((R.drawable.canada))
                    imageView.setImageResource(R.drawable.canada)
                    countryPais.setText(R.string.canada)
                }
                if(item.name == ESTADOSUNIDOS){
                    imageView.setImageResource((R.drawable.estados_unidos))
                    countryPais.setText(R.string.estadosUnidos)
                }
                if(item.name == JAPAO){
                    imageView.setImageResource(R.drawable.japao)
                    countryPais.setText(R.string.japao)
                }
                if(item.name == PORTUGAL){
                    imageView.setImageResource((R.drawable.portugal))
                    countryPais.setText(R.string.portugal)
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





