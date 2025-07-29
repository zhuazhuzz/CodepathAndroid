package com.example.speedconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.widget.addTextChangedListener
import com.example.speedconverter.databinding.LayoutBinding
import com.example.speedconverter.ui.theme.SpeedConverterTheme
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : ComponentActivity() {
    private lateinit var title: LayoutBinding
    private lateinit var binding: LayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.msEnter.addTextChangedListener { calculateSpeed() }
        binding.speed.setOnClickListener { randomSpeedText() }

    }

    private fun randomSpeedText() {
        var preText:String = "You are as fast as a "
        var speeds = arrayOf<Int>(1, 5, 7, 27, 145, 600, 7823)
        var speedsNames = arrayOf("walker", "runner", "biker", "car", "propeller plane", "jet fighter", "space station")

        var rng:Int = (0..6).random()
        binding.msEnter.setText(speeds[rng].toString())
        binding.speedText.setText(preText.plus(speedsNames[rng]))
    }

    private fun calculateSpeed() {
        binding.speedText.setText("")
        if(binding.msEnter.getText().toString().isEmpty()) {
            binding.mphspeed.setText("")
            binding.kmphspeed.setText("")
            binding.machspeed.setText("")
        } else {
            var msVal = binding.msEnter.getText().toString().toDouble()

            var mphVal = BigDecimal(msVal * 2.237).setScale(2, RoundingMode.HALF_EVEN)
            var kmphVal = BigDecimal(msVal * 3.6).setScale(2, RoundingMode.HALF_EVEN)
            var machVal = BigDecimal(msVal / 343).setScale(2, RoundingMode.HALF_EVEN)

            binding.mphspeed.setText(mphVal.toString())
            binding.kmphspeed.setText(kmphVal.toString())
            binding.machspeed.setText(machVal.toString())
        }

    }


}

