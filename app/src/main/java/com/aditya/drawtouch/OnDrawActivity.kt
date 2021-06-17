package com.aditya.drawtouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OnDrawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_draw)

        val myCanvasView = DrawOnTouch(this)
        // No XML file; just one custom view created programmatically.
        // Request the full available screen for layout.
        myCanvasView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = "description"
        setContentView(myCanvasView)
    }
}