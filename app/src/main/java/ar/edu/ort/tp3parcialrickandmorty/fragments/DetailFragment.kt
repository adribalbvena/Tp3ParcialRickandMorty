package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.databinding.FragmentDetailBinding
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val character = DetailFragmentArgs.fromBundle(requireArguments()).characterDto

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
            SessionManager(this.requireActivity()).toggleFavoriteId(character.id)
        }


        return binding.root
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