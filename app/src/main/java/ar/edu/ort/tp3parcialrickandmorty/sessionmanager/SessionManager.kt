package ar.edu.ort.tp3parcialrickandmorty.sessionmanager

import android.content.Context
import android.content.SharedPreferences
import ar.edu.ort.tp3parcialrickandmorty.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

class SessionManager(context: Context) {
    private var prefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    private var gson: Gson = Gson()

    companion object {
        const val USER_NAME = "user_name"
        const val FAVORITES_IDS = "favorites_ids"
        const val FAVORITES_CHECK = "favorites_enable"
        const val SEARCH_CHECK = "search_enable"
    }

    /**
     * Function to save user name
     */
    fun saveUserName(username: String) {
        val editor = prefs.edit()
        editor.putString(USER_NAME, username)
        editor.apply()
    }

    /**
     * Function to fetch user name
     */
    fun fetchUserName(): String? {
        return prefs.getString(USER_NAME, null)
    }

    /**
     * Function to get favorites ids
     */
    fun getFavoritesIds(): ArrayList<String> {
        var ids = prefs.getString(FAVORITES_IDS, null)

        if (ids != null) {
            var type = object : TypeToken<java.util.ArrayList<String?>?>() {}.getType()
            return gson.fromJson(ids, type)
        }

        return ArrayList()
    }


    /**
     * Function to toogle a favorite id
     */
    fun toggleFavoriteId(id: Int) {
        var ids = getFavoritesIds()
        if (ids.contains(id.toString())) {
            ids.remove(id.toString())
        } else {
            ids.add(id.toString())
        }

        prefs.edit().putString(FAVORITES_IDS, gson.toJson(ids)).apply()
    }

    fun getFavouritesCheck(): Boolean{
        return prefs.getBoolean(FAVORITES_CHECK, true)
    }

    fun toggleFavouritesCheck(){
        prefs.edit().putBoolean(FAVORITES_CHECK, !getFavouritesCheck()).apply()
    }

    fun getSearchCheck(): Boolean{
        return prefs.getBoolean(SEARCH_CHECK, true)
    }

    fun toggleSearchCheck(){
        prefs.edit().putBoolean(SEARCH_CHECK, !getSearchCheck()).apply()
    }

    fun deleteAll(){
        val editor = prefs.edit()
        editor.remove(FAVORITES_IDS)
        editor.remove(USER_NAME)
        editor.remove(SEARCH_CHECK)
        editor.remove(FAVORITES_CHECK)
        editor.apply()
    }
}