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
        const val NIGHT_MODE_CHECK = "night_mode_on"
        const val EMPTY_FAVORITES = "[]"
    }

    fun saveUserName(username: String) {
        prefs.edit().putString(USER_NAME, username).apply()
    }

    fun getUserName(): String? {
        return prefs.getString(USER_NAME, null)
    }

    fun getFavoritesIds(): ArrayList<Int> {
        val ids = prefs.getString(FAVORITES_IDS, EMPTY_FAVORITES)
        val type = object : TypeToken<java.util.ArrayList<Int?>?>() {}.type
        return gson.fromJson(ids, type)
    }

    fun toggleFavoriteId(id: Int) {
        val ids = getFavoritesIds()
        if (ids.contains(id)) ids.remove(id) else ids.add(id)
        prefs.edit().putString(FAVORITES_IDS, gson.toJson(ids)).apply()
    }

    fun getFavouritesCheck(): Boolean {
        return prefs.getBoolean(FAVORITES_CHECK, true)
    }

    fun toggleFavouritesCheck() {
        prefs.edit().putBoolean(FAVORITES_CHECK, !getFavouritesCheck()).apply()
    }

    fun getSearchCheck(): Boolean {
        return prefs.getBoolean(SEARCH_CHECK, true)
    }

    fun toggleSearchCheck() {
        prefs.edit().putBoolean(SEARCH_CHECK, !getSearchCheck()).apply()
    }

    fun getNightModeCheck(): Boolean {
        return prefs.getBoolean(NIGHT_MODE_CHECK, false)
    }

    fun toggleNightModeCheck() {
        prefs.edit().putBoolean(NIGHT_MODE_CHECK, !getNightModeCheck()).apply()
    }

    fun deleteAll() {
        val editor = prefs.edit()
        editor.remove(FAVORITES_IDS)
        editor.remove(USER_NAME)
        editor.remove(SEARCH_CHECK)
        editor.remove(FAVORITES_CHECK)
        editor.remove(NIGHT_MODE_CHECK)
        editor.apply()
    }
}