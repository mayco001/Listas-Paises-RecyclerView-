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
import com.mayco.listadepaises.utils.Constants.ESTADOSUNIDOS
import com.mayco.listadepaises.utils.Constants.JAPAO
import com.mayco.listadepaises.utils.Constants.PORTUGAL

class MainActivity : AppCompatActivity() {

    private var searchView: SearchView? = null
    private var adapterPais = CountryAndapter()
    private var listPais: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pais: List<Pais> = listOf(
            Pais(
                name = BRASIL,
                image = R.drawable.brasil,
                discretion = getString(R.string.brasil)
            ),
            Pais(
                name = ALEMANHA,
                image = R.drawable.alemanha,
                discretion = getString(R.string.alemanha)
            ),
            Pais(
                name = CANADA,
                image = R.drawable.canada,
                discretion = getString(R.string.canada)
            ),
            Pais(
                name = PORTUGAL,
                image = R.drawable.portugal,
                discretion = getString(R.string.portugal)
            ),
            Pais(
                name = JAPAO,
                image = R.drawable.japao,
                discretion = getString(R.string.japao)
            ),
            Pais(
                name = ESTADOSUNIDOS,
                image = R.drawable.estados_unidos,
                discretion = getString(R.string.estadosUnidos)
            )
        )

        listPais = findViewById(R.id.recylerCountry)
        searchView = findViewById(R.id.searchView)

        adapterPais.items = pais
        adapterPais.paisList = pais
        setupRecyclerView()
        adapterPais.notifyDataSetChanged()

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
