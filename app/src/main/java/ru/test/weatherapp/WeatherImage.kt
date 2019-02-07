package ru.test.weatherapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class WeatherImage(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()

    init {
        paint.color = Color.YELLOW
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = (canvas.width / 2).toFloat()
        val centerY = (canvas.height / 2).toFloat()
        canvas.drawCircle(centerX, centerY, 60f, paint)

        for (i in 1..50) {
            val newX: Float = (Math.random() * 90).toFloat()
            val newY: Float = (Math.random() * 90).toFloat()
            canvas.drawLine(centerX - newX, centerY - newY, centerX + newX, centerY + newY, paint)
            canvas.drawLine(centerX + newX, centerY - newY, centerX - newX, centerY + newY, paint)
        }
    }

}
