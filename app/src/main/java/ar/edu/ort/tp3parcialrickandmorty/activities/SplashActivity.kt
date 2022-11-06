package ar.edu.ort.tp3parcialrickandmorty.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_TIME_OUT:Long = 4000 // 4 seconds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                validateLogin()
            }
            , SPLASH_TIME_OUT)
    }

    fun validateLogin(){
        var username = SessionManager(this).fetchUserName()

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

