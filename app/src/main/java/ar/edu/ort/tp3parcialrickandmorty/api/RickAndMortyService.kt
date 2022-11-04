package ar.edu.ort.tp3parcialrickandmorty.api

import ar.edu.ort.tp3parcialrickandmorty.interfaces.rickandmortyAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RickAndMortyService {

    companion object{
        private const val BASE_URL= "https://rickandmortyapi.com/api/"

        fun create(): rickandmortyAPI {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(rickandmortyAPI::class.java)
        }
    }
}