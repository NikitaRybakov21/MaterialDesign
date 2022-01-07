package com.example.materialdesign.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CustomImageView @JvmOverloads constructor(context : Context, attrs : AttributeSet? = null, style: Int = 0)
    : AppCompatImageView(context,attrs,style) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}