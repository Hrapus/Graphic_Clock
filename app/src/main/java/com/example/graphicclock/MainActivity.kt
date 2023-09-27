package com.example.graphicclock

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
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
        val orientation = this.resources.configuration.orientation




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


        viewModel.isDotsOn.observe(this) { it ->

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                binding.imDots.setImageResource(R.drawable.dots00)
            } else {
                if (it == true) {
                    binding.imDots.setImageResource(R.drawable.dots0)
                } else {
                    binding.imDots.setImageResource(R.drawable.dots00)
                }
            }
        }
        viewModel.getTime()
    }

}

private fun getImageResFromDigit(digit: Int?): Int {
    return when (digit) {
        0 -> R.drawable.zero0
        1 -> R.drawable.one1
        2 -> R.drawable.two2
        3 -> R.drawable.three3
        4 -> R.drawable.four4
        5 -> R.drawable.five5
        6 -> R.drawable.six6
        7 -> R.drawable.seven7
        8 -> R.drawable.eight8
        9 -> R.drawable.nine9
        else -> {
            R.drawable.zero0
        }
    }
}
