package com.aditya.drawtouch

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import kotlin.math.abs


class OnEraseBackground: View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var stroke = 50f
    private var path = Path()
    private val mSourceCanvas = Canvas()
    private var currentX = 0f
    private var currentY = 0f
    private var motionTouchEventX: Float = 0f
    private var motionTouchEventY: Float = 0f
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pp)
    private lateinit var mSourceBitmap: Bitmap
    private lateinit var m: Matrix

    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        alpha = 0
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = stroke // default: Hairline-width (really thin)
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        m = Matrix()
        m.setRectToRect(RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat()), RectF(0f, 0f, w.toFloat(), h.toFloat())
                , Matrix.ScaleToFit.CENTER)
        mSourceBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mSourceCanvas.setBitmap(mSourceBitmap)
        mSourceCanvas.drawBitmap(bitmap, m, null)
        mSourceCanvas.drawPath(path, paint)

        //Draw bitmap
        canvas?.drawBitmap(mSourceBitmap, matrix, null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
        }
        return true
    }

    private fun touchStart() {
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
        }
        // Invalidate() is inside the touchMove() under ACTION_MOVE because there are many other
        // types of motion events passed into this listener, and we don't want to invalidate the
        // view for those.
        invalidate()
    }

}