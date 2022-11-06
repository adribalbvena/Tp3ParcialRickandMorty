package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.databinding.FragmentDetailBinding
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val character = DetailFragmentArgs.fromBundle(requireArguments()).characterDto
        sessionManager = SessionManager(this.requireActivity())

        binding.tvDetailStatus.text= character.status
        binding.tvDetailName.text= character.name
        binding.tvSpecie.text = character.species
        binding.tvOrigin.text = character.origin

        when(character.status){
            "Dead" -> setTextViewDrawableColor(binding.tvDetailStatus, R.color.dead)
            "unknown" -> setTextViewDrawableColor(binding.tvDetailStatus, R.color.unknow)
            "Alive" -> setTextViewDrawableColor(binding.tvDetailStatus, R.color.alive)
        }

        Glide.with(binding.root)
            .load(character.image)
            .into(binding.ivDetail)

        binding.favButton.setOnClickListener{
            //Aca deberian agregar a favoritos pasando el character que trajimos mas arriba (val character)
            sessionManager.toggleFavoriteId(character.id)
            toggleButton(isInFavorites(character.id.toString()))
        }
        toggleButton(isInFavorites(character.id.toString()))

        if( !sessionManager.getFavouritesCheck()){
            binding.favButton.visibility = View.GONE
        }
        return binding.root
    }

    private fun isInFavorites(id:String):Boolean {
         return sessionManager.getFavoritesIds().contains(id)
    }

    private fun toggleButton(isAdded: Boolean){
        if(!isAdded) {
            binding.favButton.setImageResource(android.R.drawable.ic_input_add)
        } else {
            binding.favButton.setImageResource(android.R.drawable.ic_menu_delete)
        }
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

}