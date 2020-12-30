package com.example.projet4a.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projet4a.R
import kotlinx.android.synthetic.main.pokemon_main.*

class PokemonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_main)

        toolbar.setTitle("DES DIGIMOM OMG")
        setSupportActionBar(toolbar)
    }
}