package ar.edu.ort.tp3parcialrickandmorty.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import ar.edu.ort.tp3parcialrickandmorty.R
import ar.edu.ort.tp3parcialrickandmorty.adapters.CharacterListAdapter
import ar.edu.ort.tp3parcialrickandmorty.api.RickAndMortyService
import ar.edu.ort.tp3parcialrickandmorty.data.Character
import ar.edu.ort.tp3parcialrickandmorty.data.CharacterDto
import ar.edu.ort.tp3parcialrickandmorty.databinding.FragmentFavoritesBinding
import ar.edu.ort.tp3parcialrickandmorty.listeners.OnCharacterClickedListener
import ar.edu.ort.tp3parcialrickandmorty.sessionmanager.SessionManager
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesFragment : Fragment(), OnCharacterClickedListener {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favouritesRecyclerAdapter: CharacterListAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        sessionManager = SessionManager(this.requireActivity())
        binding.textViewFavoritesGreeting.text =
            String.format(getString(R.string.greeting), sessionManager.getUserName())
        initFavouritesRecyclerView()
        getFavorites()
        return binding.root
    }

    private fun initFavouritesRecyclerView() {
        gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.favoritesRv.layoutManager = gridLayoutManager
        favouritesRecyclerAdapter = CharacterListAdapter(this)
        binding.favoritesRv.adapter = favouritesRecyclerAdapter
    }

    private fun getFavorites() {
        val favouritesList = sessionManager.getFavoritesIds()

        if (favouritesList.isEmpty()) {
            return
        }

        val list = favouritesList.fold("") { result, id -> "$result,$id" }.trim(',')
        val call = RickAndMortyService.create().getFavourites("[$list]")
        call.enqueue(object : Callback<ArrayList<Character>> {
            override fun onResponse(
                call: Call<ArrayList<Character>>,
                response: Response<ArrayList<Character>>
            ) {
                if (response.isSuccessful) {
                    favouritesRecyclerAdapter.setList(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<Character>>, t: Throwable) {
                Snackbar.make(
                    requireView(),
                    getString(R.string.error_message),
                    Snackbar.LENGTH_LONG
                ).show()
                Log.d("Failed to load characters", t.message.toString())
            }

        })
    }

    override fun onCharacterSelected(character: Character) {
        val characterDto = CharacterDto(
            character.id,
            character.image,
            character.name,
            character.origin.name,
            character.species,
            character.status
        )

        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(characterDto)
        findNavController().navigate(action)
    }
}