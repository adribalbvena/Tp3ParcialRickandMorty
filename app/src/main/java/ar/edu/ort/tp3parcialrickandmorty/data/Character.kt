package ar.edu.ort.tp3parcialrickandmorty.data

import com.google.gson.annotations.SerializedName

data class Character(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("origin") val origin: Origin,
    @field:SerializedName("species") val species: String,
    @field:SerializedName("status") val status: String,
)