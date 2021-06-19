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
        // No XML file; just one custom view created programmatically.
        // Request the full available screen for layout.
        myCanvasView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = "description"
        setContentView(myCanvasView)
    }
}