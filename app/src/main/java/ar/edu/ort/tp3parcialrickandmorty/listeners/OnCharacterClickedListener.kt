package ar.edu.ort.tp3parcialrickandmorty.listeners

import ar.edu.ort.tp3parcialrickandmorty.data.Character

interface OnCharacterClickedListener {
    fun onCharacterSelected(character: Character)
}