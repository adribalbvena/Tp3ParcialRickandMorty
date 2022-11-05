package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.edu.ort.tp3parcialrickandmorty.databinding.FragmentSettingsBinding
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sessionManager = SessionManager(this.requireActivity())
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.switchSettingsFavourites.isChecked = sessionManager.getFavouritesCheck()
        binding.switchSettingsFavourites.setOnClickListener{ sessionManager.toggleFavouritesCheck() }
        return binding.root
    }
}