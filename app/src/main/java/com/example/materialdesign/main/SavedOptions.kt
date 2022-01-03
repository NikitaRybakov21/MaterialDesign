package com.example.materialdesign.main

import android.annotation.SuppressLint
import android.content.Context

class SavedOptions(private val activity: MainActivity) {

    @SuppressLint("CommitPrefEdits")
    fun save(key : String, value : Boolean){
        val editor = activity.getPreferences(Context.MODE_PRIVATE).edit()
        editor.putBoolean(key,value).apply()
    }

    fun load(key: String) : Boolean {
        return activity.getPreferences(Context.MODE_PRIVATE).getBoolean(key,false)
    }

    companion object{
        fun getOptions(activity: MainActivity) = SavedOptions(activity)
    }
}