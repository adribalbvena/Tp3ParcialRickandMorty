package ar.edu.ort.tp3parcialrickandmorty.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.appcompat.app.AppCompatDelegate
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager

class SplashActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    companion object {
        private const val SPLASH_TIME_OUT: Long = 4000 // 4 seconds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        setContentView(R.layout.activity_splash)
        setNightMode()
        Handler().postDelayed({ validateLogin() }, SPLASH_TIME_OUT)
    }

    private fun setNightMode() {
        AppCompatDelegate.setDefaultNightMode(if (sessionManager.getNightModeCheck()) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun validateLogin() {
        val username = sessionManager.getUserName()

        if (username != null && !TextUtils.isEmpty(username)) {
            startMainActivity()
            return
        }
        startLoginActivity()
    }

    private fun startMainActivity() {
        this.startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }

    private fun startLoginActivity() {
        this.startActivity(Intent(this, LoginActivity::class.java))
        this.finish()
    }
}

