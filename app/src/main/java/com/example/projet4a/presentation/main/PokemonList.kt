package com.example.projet4a.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet4a.R
import com.example.projet4a.presentation.adapter.PokemonListAdapter
import com.example.projet4a.presentation.commom.Common
import com.example.projet4a.presentation.commom.ItemOffsetDecoration
import com.example.projet4a.presentation.retrofit.IPokemonList
import com.example.projet4a.presentation.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonList : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var iPokemonList:IPokemonList

    internal lateinit var recyclerView: RecyclerView

    init {
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        recyclerView = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        val itemDecoration = ItemOffsetDecoration(activity!!, R.dimen.spacing)
        recyclerView.addItemDecoration(itemDecoration)

        fetchData()

        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokemonListAdapter(activity!!,Common.pokemonList)

                recyclerView.adapter = adapter
            }
        )
    }
}
