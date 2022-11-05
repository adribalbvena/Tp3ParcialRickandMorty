package ar.edu.ort.tp3parcialrickandmorty.interfaces

import ar.edu.ort.tp3parcialrickandmorty.data.CharacterResponse
import ar.edu.ort.tp3parcialrickandmorty.data.FavoriteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ar.edu.ort.tp3parcialrickandmorty.data.Character

interface rickandmortyAPI {
    @GET("character")
    fun getCharacters(): Call<CharacterResponse>

    @GET("character")
    fun searchCharacter(@Query("name") searchText: String): Call<CharacterResponse>

    @GET("character/{ids}")
    fun getFavourites(@Path("ids") searchText: String): Call<ArrayList<Character>>

}