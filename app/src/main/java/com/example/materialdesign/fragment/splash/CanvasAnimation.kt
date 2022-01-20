package com.example.materialdesign.fragment.splash

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class CanvasAnimation(context: Context) : View(context) {
    private val paint = Paint()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!

        canvas.drawColor(Color.rgb(222, 240, 173))

        paint.color = Color.rgb(18, 28, 54)
        paint.strokeWidth = 4f
        paint.textSize = 80f

        animationLine1(canvas)
        animationLine2(canvas)

        textAnimation(canvas)
        lineAnimation(canvas)

        invalidate()
    }
    private var i = 0.0
    private var j = 0.0
    private var k = 0.0

    private fun textAnimation(canvas: Canvas){
        paint.color = Color.rgb(18, 28, 54)
        paint.textSize = 80f

        val string = "Material"
        val array  =  string.toCharArray()
        canvas.drawText(array,0, i.toInt() ,(width/4).toFloat(),(height/2 - height/7).toFloat(),paint)

        if (i < string.length){
            i += 0.15
        } else {
            val string2 = "Design"
            val array2  =  string2.toCharArray()
            canvas.drawText(array2,0, j.toInt() ,(width/4).toFloat(),(height/2 - height/10).toFloat(),paint)

            if (j < string2.length) {
                j += 0.15
            } else {
                loadingAnimation(canvas)
                loadingRectAnimation(canvas)
            }
        }
    }
    private var persent = 0.0

    private fun loadingAnimation(canvas: Canvas){
        paint.color = Color.rgb(70, 78, 99)
        paint.textSize = 55f

        val string = "Loading"
        val array  =  string.toCharArray()
        canvas.drawText(array,0, k.toInt() ,(width/4 - width/20).toFloat(),(height/2 - height/38).toFloat(),paint)

        if (k < string.length) {
            k += 0.2
        } else {
            canvas.drawText("${persent.toInt()} %",(width/2).toFloat(),(height/2 - height/38).toFloat(),paint)
            if(persent < 100) {
                persent += 0.23
            }
        }
    }
    private val arrayRect = ArrayList<Rect>(16)

    private var rectX = 0
    private var kRect = 0
    private var insert = 0
    private var saveX = 0
    private var flagR = true

    private fun loadingRectAnimation(canvas: Canvas){
        paint.color = Color.rgb(124, 136, 145)
        val startY = (height/2)

        if (flagR) {
            for (i in 0..6) { arrayRect.add(Rect(0, startY, 0 + 80, startY + 80)) }
            flagR = false
        }
        for (i in 0..6) { canvas.drawRect(arrayRect[i], paint) }

        if (kRect < arrayRect.size) {
            arrayRect[kRect].set(rectX,startY,rectX + 80,startY + 80)
        }

        if(kRect < arrayRect.size) {
            if (rectX < (width / 2 + width / 5) - insert) {
                rectX += 15
            } else {
                rectX = 0
                kRect++
                insert += 80 + 10
            }
        } else {
            kRect  = 0
            insert = 0
            rectX  = 0
            flagR = true
            arrayRect.clear()
        }
        paint.color = Color.rgb(222, 240, 173)
        canvas.drawRect( Rect(0,startY,saveX - 2,(height/2 + height/10)), paint )
    }
    private var flag = true
    private var endX = 0.0

    private var flag2 = true
    private var endX2 = 0.0

    private var vector = 0.0

    private var endY1 = 0.0
    private var endY2 = 0.0

    private fun animationLine1(canvas: Canvas){


        if (flag){
            endX = (width/2 + width/3).toDouble()
            flag = false
        }
        val startX = (width/2  + width/3)  .toFloat()
        val startY = (height/2 - height/16).toFloat()

        if(endX > (width/2 - width/(3.5))) {
            endX -= 12
        } else {
            val x = (endX   + vector * cos(-5 * PI/4)).toFloat()
            val y = (startY + vector * sin(-5 * PI/4)).toFloat()

            if(vector < height/20){
                vector += 2
            } else {
                saveX = x.toInt()
                if(endY1 < abs(y - (height/2 + height/16))){
                    endY1 += 5
                }
                canvas.drawLine(x, y, x , y + endY1.toFloat() , paint)
            }
            canvas.drawLine(endX.toFloat(), startY, x , y , paint)
        }
        canvas.drawLine(startX, startY, endX.toFloat(), startY, paint)
    }

    private fun animationLine2(canvas: Canvas){
        if (flag2){
            endX2 = (width/2 - width/3).toDouble()
            flag2 = false
        }
        val startX2 = (width/2  - width/3).toFloat()
        val startY2 = (height/2 + height/16).toFloat()

        if(endX2 < (width/2 + width/(3.5))) {
            endX2 += 12
        } else {
            val x = (endX2   + vector * cos(-PI/4)).toFloat()
            val y = (startY2 + vector * sin(-PI/4)).toFloat()

            if(vector < height/20){
                vector += 2
            } else {
                if(endY2 < abs(y - (height/2 - height/16))){
                    endY2 += 5
                }
                canvas.drawLine(x, y, x ,y - endY2.toFloat() , paint)
            }
            canvas.drawLine(endX2.toFloat(), startY2, x , y , paint)
        }
        canvas.drawLine(startX2, startY2, endX2.toFloat(), startY2, paint)
    }
    private var y = 1
    private var end = 10.0

    private fun lineAnimation(canvas: Canvas){
        paint.color = Color.rgb(18, 28, 54)
        paint.strokeWidth = 3f

        y = 0
        while (y <= end) {
            canvas.drawLine((width / 4 - width / 38).toFloat(), (-50 + y + (height / 2 - height / 7)).toFloat(),
                            (width / 4 - width / 10).toFloat(), (-50 + y + (height / 2 - height / 7)).toFloat(), paint)
            y += 20
        }
        if(end < 140) {
            end += 1.5
        }
    }
}