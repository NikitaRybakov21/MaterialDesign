package com.example.materialdesign.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.materialdesign.fragment.PictureFragment
import com.example.materialdesign.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PictureFragment.newInstance())
                .commit()
        }
    }
}