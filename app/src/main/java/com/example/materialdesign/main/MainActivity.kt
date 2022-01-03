package com.example.materialdesign.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.materialdesign.fragment.PictureFragment
import com.example.materialdesign.R

class MainActivity : AppCompatActivity() {
    private val KEY = "Theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val options = SavedOptions.getOptions(this)
        val op = options.load(KEY)

        if(op){
            setTheme(R.style.ThemeDark)
        }else{
            setTheme(R.style.ThemeLight)
        }

        setContentView(R.layout.activity_main)


        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureFragment.newInstance())
                .commit()
        }
    }

    fun setNewTheme(){
        val options = SavedOptions.getOptions(this)
        val op = options.load(KEY)

        if(op){
            setTheme(R.style.ThemeDark)
        }else{
            setTheme(R.style.ThemeLight)
        }
        recreate()
    }
}