package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.data.CharacterDto
import ar.edu.ort.tp3parcialrickandmorty.databinding.FragmentDetailBinding
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var character: CharacterDto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(this.requireActivity())
        character = DetailFragmentArgs.fromBundle(requireArguments()).characterDto

        binding.tvDetailStatus.text = character.status
        binding.tvDetailName.text = character.name
        binding.tvSpecie.text = character.species
        binding.tvOrigin.text = character.origin

        when (character.status) {
            "Dead" -> setTextViewDrawableColor(binding.tvDetailStatus, R.color.dead)
            "unknown" -> setTextViewDrawableColor(binding.tvDetailStatus, R.color.unknow)
            "Alive" -> setTextViewDrawableColor(binding.tvDetailStatus, R.color.alive)
        }

        Glide.with(binding.root).load(character.image).into(binding.ivDetail)

        setFavoriteButtonBehaviour()
        toggleButton()

        return binding.root
    }

    private fun setFavoriteButtonBehaviour() {
        if (!sessionManager.getFavouritesCheck()) {
            binding.favButton.visibility = View.GONE
        }

        binding.favButton.setOnClickListener {
            sessionManager.toggleFavoriteId(this.character.id)
            toggleButton()
        }
    }

    private fun toggleButton() {
        var icon = android.R.drawable.ic_input_add
        //var color = R.color.greenrm
        if (sessionManager.getFavoritesIds().contains(this.character.id)) {
            icon = android.R.drawable.ic_menu_delete
            //color = R.color.dead
        }
        //TODO: change color
        binding.favButton.setImageResource(icon)
    }

    private fun setTextViewDrawableColor(textView: TextView, color: Int) {
        for (drawable in textView.compoundDrawablesRelative) {
            if (drawable != null) {
                drawable.colorFilter =
                    PorterDuffColorFilter(
                        ContextCompat.getColor(textView.context, color),
                        PorterDuff.Mode.SRC_IN
                    )
            }
        }
    }

    private fun setDrawableColor(drawable: Drawable, color: Int) {
        drawable.colorFilter =
            PorterDuffColorFilter(
                ContextCompat.getColor(this.requireContext(), color),
                PorterDuff.Mode.SRC_IN
            )
    }
}