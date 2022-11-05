package ar.edu.ort.tp3parcialrickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.data.Character
import ar.edu.ort.tp3parcialrickandmorty.holders.FavouritesViewHolder
import ar.edu.ort.tp3parcialrickandmorty.holders.HomeViewHolder
import ar.edu.ort.tp3parcialrickandmorty.listeners.OnCharacterClickedListener

class FavouritesRecyclerAdapter(private val onCharacterClickedListener: OnCharacterClickedListener) : RecyclerView.Adapter<FavouritesViewHolder>() {
    var characterList : List<Character> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return FavouritesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)

        //Ver detalle
        holder.itemView.setOnClickListener{
            onCharacterClickedListener.onCharacterSelected(character)
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun setList(characters : List<Character>) {
        this.characterList = characters
        notifyDataSetChanged();
    }
}
