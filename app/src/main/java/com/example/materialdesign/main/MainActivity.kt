package com.example.materialdesign.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.materialdesign.R
import com.example.materialdesign.fragment.mainFragments.MainContainerFragment
import com.example.materialdesign.fragment.splash.SplashFragment

class MainActivity : AppCompatActivity() {
    private val KEY = "Theme"
    private val handler: Handler by lazy { Handler(mainLooper) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadTheme()
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
                .commit()

            handler.postDelayed(Runnable {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, MainContainerFragment.newInstance())
                    .commit()
            }, 10000)
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainContainerFragment.newInstance())
                .commit()
        }
    }

    private fun loadTheme(){
        val options = SavedOptions.getOptions(this)
        val op = options.load(KEY)

        if(op){
            setTheme(R.style.ThemeDark)
        }else{
            setTheme(R.style.ThemeLight)
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