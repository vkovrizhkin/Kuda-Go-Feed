package com.angelsit.kudagofeed.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class IndicatorView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attributeSet, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val backgroundCircleColor = Color.WHITE
    private val backgroundSelectedColor = Color.RED

    var selectIndicator: Boolean = false
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cX = (width / 2).toFloat()
        val cY = (height / 2).toFloat()
        val backgroundCircleRadius = (width / 2).toFloat()

        paint.color = backgroundCircleColor
        canvas.drawCircle(
            cX,
            cY,
            backgroundCircleRadius,
            paint
        )
        if(selectIndicator){
            paint.color = backgroundSelectedColor
            val selectedCircleRadius = (width / 4).toFloat()
            canvas.drawCircle(
                cX,
                cY,
                selectedCircleRadius,
                paint
            )
        }
    }
}