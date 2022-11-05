package ar.edu.ort.tp3parcialrickandmorty.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.data.Character
import com.bumptech.glide.Glide

class FavouritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val name: TextView
    private val status: TextView
    private val image: ImageView

    init {
        name = itemView.findViewById(R.id.tvNameCharacter)
        status = itemView.findViewById(R.id.tvStatusCharacter)
        image = itemView.findViewById(R.id.ivCharacter)
    }

    fun bind(character: Character) {
        name.text = character.name
        status.text = character.status

        Glide.with(itemView)
            .load(character.image)
            .into(image)
    }

}