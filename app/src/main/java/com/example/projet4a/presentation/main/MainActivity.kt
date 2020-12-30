package com.example.projet4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.projet4a.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.loginLiveData.observe(this, Observer {
            when(it){
                is LoginSuccess -> {
                    val monIntent : Intent =  Intent(this, PokemonActivity::class.java)
                    startActivity(monIntent)
                }

                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Tu t'es tromper chacal")
                        .setMessage("Compte inexistant \nVeuillez créer un compte !\nClique sur OK si t'as capter chacal")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                    val monIntent : Intent =  Intent(this, PokemonActivity::class.java)
                    startActivity(monIntent)
                }
            }
        })
        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString().trim())
        }

    }
}
