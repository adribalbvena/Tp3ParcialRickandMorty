package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
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
        setSettingsSwitches()
        return binding.root
    }

    private fun setSettingsSwitches() {
        binding.switchSettingsFavourites.isChecked = sessionManager.getFavouritesCheck()
        binding.switchSettingsFavourites.setOnClickListener { sessionManager.toggleFavouritesCheck() }

        binding.switchSettingsSearch.isChecked = sessionManager.getSearchCheck()
        binding.switchSettingsSearch.setOnClickListener { sessionManager.toggleSearchCheck() }

        binding.switchSettingsNightMode.isChecked = sessionManager.getNightModeCheck()
        binding.switchSettingsNightMode.setOnCheckedChangeListener { _, isChecked ->
            sessionManager.toggleNightModeCheck()
            AppCompatDelegate.setDefaultNightMode(if (isChecked) MODE_NIGHT_YES else MODE_NIGHT_NO)
        }
    }
}