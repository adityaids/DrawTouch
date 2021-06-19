 package com.aditya.drawtouch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import com.aditya.drawtouch.databinding.ActivityImageFilterViewBinding
import com.github.chrisbanes.photoview.PhotoViewAttacher

class ImageFilterViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageFilterViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageFilterViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imagefilterview.setImageResource(R.drawable.pp)
        //Get the first-run setting of crossfade, saturation, contrast and warmth
        binding.crossfade.text= binding.imagefilterview.crossfade.toString()
        binding.sat.text= binding.imagefilterview.saturation.toString()
        binding.cont.text= binding.imagefilterview.contrast.toString()
        binding.warm.text= binding.imagefilterview.warmth.toString()

        binding.seekBarCrossfade.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,
                                               progress: Int, fromUser: Boolean) {
                    //update saturation
                    binding.imagefilterview.crossfade = (progress / 100.0f)
                    //read back the saturation
                    binding.crossfade.text= binding.imagefilterview.crossfade.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        binding.seekBarSat.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,
                                               progress: Int, fromUser: Boolean) {
                    //update saturation
                    binding.imagefilterview.saturation = (progress / 100.0f) * 2
                    //read back the saturation
                    binding.sat.text= binding.imagefilterview.saturation.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        binding.seekBarCont.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,
                                               progress: Int, fromUser: Boolean) {
                    binding.imagefilterview.contrast = (progress / 100.0f) * 2
                    binding.cont.text= binding.imagefilterview.contrast.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        binding.seekBarWarm.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,
                                               progress: Int, fromUser: Boolean) {
                    binding.imagefilterview.warmth = (progress / 100.0f) * 2
                    binding.warm.text= binding.imagefilterview.warmth.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

        val attacher = PhotoViewAttacher(binding.imagefilterview)
        attacher.isZoomable = true
    }
}