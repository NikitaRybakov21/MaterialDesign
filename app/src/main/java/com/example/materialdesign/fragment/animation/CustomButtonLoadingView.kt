package com.example.materialdesign.fragment.animation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CustomButtonLoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, style: Int = 0) : View(context, attrs, style) {
    private var flagAnimation = false

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        canvas!!

        paintCircleButton1(canvas, paint)
        if(flagAnimation) { paintCircleLoading(canvas, paint) }
        paintCircleButton2(canvas, paint)
    }
    private fun paintCircleButton1(canvas: Canvas,paint: Paint){
        val a = width
        paint.color = Color.LTGRAY
        canvas.drawCircle((a/2).toFloat(), (a/2).toFloat(), ((a/2)*0.98).toFloat(), paint);
    }

    private fun paintCircleButton2(canvas: Canvas,paint: Paint){
        val a = width
        paint.color = Color.GRAY
        canvas.drawCircle((a/2).toFloat(), (a/2).toFloat(), ((a/2)*0.7).toFloat(), paint);
    }

    private var alphaEND = PI / 2
    private fun paintCircleLoading(canvas: Canvas, paint: Paint) {
        val a = width
        var alpha = PI / 2

        while (alpha >= alphaEND) {
            val vector = a / 2
            val x = (vector * cos(alpha)) + a / 2
            val y = (vector * sin(alpha)) + a / 2
            alpha -= 0.01

            paint.color = Color.GREEN
            canvas.drawLine((a / 2).toFloat(), ((a / 2)).toFloat(), x.toFloat(), (-y + a).toFloat(), paint)
        }
        if (alphaEND >= -(3 * PI) / 2) {
            alphaEND -= 0.04
        }
        invalidate()
    }

    fun setAnimation(flag : Boolean){
        flagAnimation = flag
    }
}
