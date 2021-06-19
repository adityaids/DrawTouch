package com.aditya.drawtouch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import com.aditya.drawtouch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOndraw.setOnClickListener(this)
        binding.toImagefilter.setOnClickListener(this)
        binding.btnToEraser.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_ondraw -> {
                val intent = Intent(this@MainActivity, OnDrawActivity::class.java)
                startActivity(intent)
            }
            R.id.to_imagefilter -> {
                val intent = Intent(this@MainActivity, ImageFilterViewActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_to_eraser -> {
                val intent = Intent(this@MainActivity, RemoveBackgroundActivity::class.java)
                startActivity(intent)
            }
        }
    }
}