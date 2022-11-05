package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.activities.LoginActivity
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager

class LogoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onLogOut()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logout, container, false)
    }

    fun onLogOut(){
        var myactivity = this.requireActivity()
        var sessionManager = SessionManager(myactivity)
        sessionManager.deleteUserName()
        sessionManager.deleteFavourites()
        myactivity.startActivity(Intent(myactivity, LoginActivity::class.java))
        myactivity.finish()
    }
}