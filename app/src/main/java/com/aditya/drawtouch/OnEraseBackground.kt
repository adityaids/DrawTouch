package com.aditya.drawtouch

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

class OnEraseBackground(context: Context): View(context) {
    private var stroke = 50f
    private var path = Path()
    private val mSourceCanvas = Canvas()
    private var currentX = 0f
    private var currentY = 0f
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    val bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pp)
    val mSourceBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888)
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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mSourceCanvas.setBitmap(mSourceBitmap)
        mSourceCanvas.drawBitmap(bitmap, 0f, 0f, null)
        mSourceCanvas.drawPath(path, paint)

        //Draw bitmap
        canvas?.drawBitmap(mSourceBitmap, 0f, 0f, null);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
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

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }
}