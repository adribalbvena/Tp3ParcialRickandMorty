package ar.edu.ort.tp3parcialrickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.data.Character
import ar.edu.ort.tp3parcialrickandmorty.holders.HomeViewHolder

class HomeRecyclerAdapter() : RecyclerView.Adapter<HomeViewHolder>() {
    var characterList : List<Character> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
    }

    override fun getItemCount(): Int = characterList.size

    fun setList(characters : List<Character>) {
        this.characterList = characters
        notifyDataSetChanged();
    }
}
