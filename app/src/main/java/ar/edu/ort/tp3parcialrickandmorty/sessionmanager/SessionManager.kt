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
     * Function to delete user name
     */
    fun deleteUserName() {
        val editor = prefs.edit()
        editor.remove(USER_NAME)
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
     * Function to store a favorite id
     */
    fun addFavoriteId(id: Int) {
        var ids = getFavoritesIds()
        ids.add(id.toString())
        prefs.edit().putString(FAVORITES_IDS, gson.toJson(ids)).apply()
    }

    /**
     * Function to remove a favorite id
     */
    fun removeFavoriteId(id: Int) {
        var ids = getFavoritesIds()
        if (ids.isEmpty()) {
            return
        }
        ids.remove(id.toString())
        prefs.edit().putString(FAVORITES_IDS, gson.toJson(ids)).apply()
    }
}