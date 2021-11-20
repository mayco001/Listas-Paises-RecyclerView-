package com.mayco.listadepaises

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mayco.listadepaises.adapter.CountryAndapter
import com.mayco.listadepaises.model.Pais
import com.mayco.listadepaises.utils.Constants.ALEMANHA
import com.mayco.listadepaises.utils.Constants.BRASIL
import com.mayco.listadepaises.utils.Constants.CANADA


class MainActivity : AppCompatActivity() {

    private var searchView: SearchView ?= null
    private var adapterPais = CountryAndapter()
    private var listPais: RecyclerView ?= null
    val pais: List<Pais> = listOf( Pais(BRASIL), Pais(ALEMANHA), Pais(CANADA) )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Comentario


        listPais = findViewById(R.id.recylerCountry)
        searchView = findViewById(R.id.searchView)



        adapterPais.items = pais
        adapterPais.paisList = pais
        setupRecyclerView()
        adapterPais.notifyDataSetChanged()


        // Error ...
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override
            fun onQueryTextSubmit(query: String?): Boolean {
                adapterPais.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterPais.filter.filter(newText)
                return true
            }

        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        listPais?.adapter = adapterPais
        val manager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        listPais?.layoutManager = manager
    }
}

