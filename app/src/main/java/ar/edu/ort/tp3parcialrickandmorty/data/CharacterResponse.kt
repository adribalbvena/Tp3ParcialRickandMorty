package ar.edu.ort.tp3parcialrickandmorty.data

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @field:SerializedName("results") val results: List<Character>
)