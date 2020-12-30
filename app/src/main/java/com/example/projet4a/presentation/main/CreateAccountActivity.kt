package com.example.projet4a.presentation.main

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.lifecycle.Observer
import com.example.projet4a.R
import com.example.projet4a.domain.entity.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class CreateAccountActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    fun isValidEmail(target: CharSequence?): Boolean {
        return target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        validation_button.setOnClickListener() {
            val monIntent : Intent =  Intent(this, MainActivity::class.java)
            val user = User(login_edit2.text.toString(), password_edit2.text.toString())
            if (user.email != "" && isValidEmail(user.email) && user.password != "") {
                mainViewModel.onClickedValidation(user)
                startActivity(monIntent)
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Tu t'es trompé chacal")
                    .setMessage("Soit tu n'as pas mis une adresse mail\nSoit tes deux mots de passes sont différents\nClique sur OK si t'as capté chacal")
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
