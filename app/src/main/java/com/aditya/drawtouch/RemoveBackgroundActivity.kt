package com.aditya.drawtouch

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class RemoveBackgroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_background)

        val myCanvasView = OnEraseBackground(this)
        myCanvasView.contentDescription = "description"
    }
}