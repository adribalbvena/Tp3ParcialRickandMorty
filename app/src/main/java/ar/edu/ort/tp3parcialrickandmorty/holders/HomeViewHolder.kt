package ar.edu.ort.tp3parcialrickandmorty.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.data.Character

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val name: TextView
    private val status: TextView

    init {
        name = itemView.findViewById(R.id.tvNameCharacter)
        status = itemView.findViewById(R.id.tvStatusCharacter)
    }

    fun bind(character: Character) {
        name.text = character.name
        status.text = character.status
    }

}