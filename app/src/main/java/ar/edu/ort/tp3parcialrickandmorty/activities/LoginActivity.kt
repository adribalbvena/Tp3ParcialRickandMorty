package ar.edu.ort.tp3parcialrickandmorty.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import ar.edu.ort.tp3parcialrickandmorty.databinding.ActivityLoginBinding
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLoginSignin.setOnClickListener { onLoginButtonClick() }
    }

    private fun onLoginButtonClick() {
        val username = binding.editTextLoginUsername.text.toString()
        val password = binding.editTextLoginPassword.text.toString()

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty((password))) {
            return
        }

        SessionManager(this).saveUserName(username)
        startMainActivity()
    }

    private fun startMainActivity() {
        this.startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }
}