package ar.edu.ort.tp3parcialrickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.data.Character
import ar.edu.ort.tp3parcialrickandmorty.holders.CharacterListViewHolder
import ar.edu.ort.tp3parcialrickandmorty.listeners.OnCharacterClickedListener

class CharacterListAdapter(private val onCharacterClickedListener: OnCharacterClickedListener) :
    RecyclerView.Adapter<CharacterListViewHolder>() {
    private var characterList: List<Character> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val character = characterList[position]
        holder.bind(character)
        holder.itemView.setOnClickListener {
            onCharacterClickedListener.onCharacterSelected(character)
        }
    }

    override fun getItemCount(): Int = characterList.size

    fun setList(characters: List<Character>) {
        this.characterList = characters
        notifyDataSetChanged()
    }
}
