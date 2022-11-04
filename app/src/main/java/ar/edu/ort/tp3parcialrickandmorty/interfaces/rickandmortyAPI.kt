package ar.edu.ort.tp3parcialrickandmorty.interfaces

import ar.edu.ort.tp3parcialrickandmorty.data.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface rickandmortyAPI {
    @GET("character")
    fun getCharacters(): Call<CharacterResponse>
}