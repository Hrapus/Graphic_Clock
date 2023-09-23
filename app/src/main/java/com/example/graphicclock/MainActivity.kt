package com.example.graphicclock

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.graphicclock.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ClockViewModel by lazy {
        ViewModelProvider(this)[ClockViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel.getTime()

        viewModel.isSecLeft.observe(this) { secLeft ->
            binding.imSecLeft.setImageResource(getImageResFromDigit(secLeft))
        }
        viewModel.isSecRight.observe(this) { secRight ->
            binding.imSecRight.setImageResource(getImageResFromDigit(secRight))
        }

        viewModel.isMinRight.observe(this) { minRight ->
            binding.imMinsRight.setImageResource(getImageResFromDigit(minRight))
        }
        viewModel.isMinLeft.observe(this) { minLeft ->
            binding.imMinsLeft.setImageResource(getImageResFromDigit(minLeft))
        }

        viewModel.isHoursRight.observe(this) { hoursRight ->
            binding.imHoursRight.setImageResource(getImageResFromDigit(hoursRight))
        }
        viewModel.isHoursLeft.observe(this) { hoursLeft ->
            binding.imHoursLeft.setImageResource(getImageResFromDigit(hoursLeft))

        }
        blinking()
    }


    fun blinking(){
        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.imDots.visibility = View.GONE
        } else {
            val anim: Animation = AlphaAnimation(0.0f, 1.0f)
            anim.duration = 10
            anim.startOffset = 500
            anim.repeatMode = Animation.REVERSE
            anim.repeatCount = Animation.INFINITE
            binding.imDots.startAnimation(anim)
        }


    }

    private fun getImageResFromDigit(digit: Int?): Int {
        return when (digit) {
            0 -> R.drawable.zero
            1 -> R.drawable.one
            2 -> R.drawable.two
            3 -> R.drawable.three
            4 -> R.drawable.four
            5 -> R.drawable.five
            6 -> R.drawable.six
            7 -> R.drawable.seven
            8 -> R.drawable.eight
            9 -> R.drawable.nine
            else -> {
                R.drawable.zero
            }
        }
    }
}