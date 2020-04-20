package net.farnetwork.latihanespresso.kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import net.farnetwork.latihanespresso.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        btnLogin.setOnClickListener {
            val username: String = textUsername.text.toString()
            val password: String = textPassword.text.toString()
            if (!validateLogin(username, password)) return@setOnClickListener
            doLogin(username, password)
        }
    }

    private fun doLogin(username: String, password: String) {
        if (username == "user" && password == "password") {
            Toast.makeText(
                    this,
                    getString(R.string.welcomeMessage, username),
                    Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, R.string.wrongUsernamePassword, Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        var isValid = true
        if (username.isEmpty()) {
            isValid = false
            textUsername.error = getString(R.string.emptyUsername)
            textUsername.isFocusable = true
        }
        if (password.isEmpty()) {
            isValid = false
            textPassword.error = getString(R.string.emptyPassword)
        }
        return isValid
    }
}
