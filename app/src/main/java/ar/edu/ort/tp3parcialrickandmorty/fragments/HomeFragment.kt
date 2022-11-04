package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.edu.ort.tp3parcialrickandmorty.adapters.HomeRecyclerAdapter
import ar.edu.ort.tp3parcialrickandmorty.api.RickAndMortyService
import ar.edu.ort.tp3parcialrickandmorty.data.CharacterResponse
import ar.edu.ort.tp3parcialrickandmorty.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeRecyclerAdapter: HomeRecyclerAdapter
    private lateinit var gridLayoutManager: GridLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initHomeRecyclerView()
        getCharacters()
        setSearchView()

        return binding.root
    }

    fun initHomeRecyclerView() {
        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.homeRv.layoutManager = gridLayoutManager
        homeRecyclerAdapter = HomeRecyclerAdapter()
        binding.homeRv.adapter = homeRecyclerAdapter
    }

    private fun setSearchView() {
        binding.searchviewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchCharacter(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchCharacter(newText!!)
                return false
            }

        })
    }

    private fun searchCharacter(query: String){
        val api = RickAndMortyService.create()
        val call = api.searchCharacter(query)
        call.enqueue(object : Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    homeRecyclerAdapter.setList(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Snackbar.make(requireView(), "Character not found", Snackbar.LENGTH_LONG).show()
                Log.d("Failed to load characters", t.message.toString())
            }

        })
    }

    private fun getCharacters() {
        val api = RickAndMortyService.create()
        val call = api.getCharacters()
        call.enqueue(object : Callback<CharacterResponse>{
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    homeRecyclerAdapter.setList(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                Snackbar.make(requireView(), "Ups! Something went wrong", Snackbar.LENGTH_LONG).show()
                Log.d("Failed to load characters", t.message.toString())
            }

        })
    }

}