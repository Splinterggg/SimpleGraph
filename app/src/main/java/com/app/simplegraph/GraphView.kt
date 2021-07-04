package com.app.simplegraph

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class GraphView : View {
    private var graphArray: List<Float>? = null
    private var maxY: Float = 0F
    private var paint: Paint? = null
    private var paintText: Paint? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint?.color = Color.BLUE
        paint?.strokeWidth = 10F
    }

    private fun setGraphArray(Xi_graphArray: List<Float>?, Xi_maxY: Float) {
        graphArray = Xi_graphArray
        maxY = Xi_maxY
    }

    fun setGraphArray(Xi_graphArray: List<Float>) {
        var maxY = 0F
        for (i in Xi_graphArray.indices) {
            if (Xi_graphArray[i] > maxY) {
                maxY = Xi_graphArray[i]
            }
        }
        setGraphArray(Xi_graphArray, maxY)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (graphArray == null) {
            return
        }
        val maxX = graphArray!!.size
        val factorX = width / maxX.toFloat()
        val factorY = height / maxY.toFloat()
        for (i in 1 until graphArray!!.size) {
            val x0 = i - 1
            val y0 = graphArray!![i - 1]
            val y1 = graphArray!![i]
            val sx = (x0 * factorX)
            val sy = height - (y0 * factorY)
            val ex = (i * factorX)
            val ey = height - (y1 * factorY)
            canvas.drawLine(sx, sy, ex, ey, paint!!)
        }
    }
}