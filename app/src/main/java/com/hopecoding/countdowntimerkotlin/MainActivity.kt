package com.hopecoding.countdowntimerkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.hopecoding.countdowntimerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isRunning = false
    private var countDownTimer: CountDownTimer? = null
    private var isReset = false
    private var isResume = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnReset.isEnabled = false
        binding.btnStart.setOnClickListener{
            if (binding.dataText.text.toString() == "" && !isRunning && !isResume && !isReset) {
                Toast.makeText(this, "You did not enter data", Toast.LENGTH_LONG).show()
            } else
                if (isRunning) {
                    pauseTime()
                } else if (isResume) {
                    resumeTime()
                } else if (isReset && binding.dataText.text.toString() == "") {
                    start2Time()
                } else {
                    startTime()
                }

        }
        binding.btnReset.setOnClickListener{ resetTime() }

    }


    private fun startTime() {
        data = binding.dataText.text.toString().toLong()
        timeLeft = data
        countDownTimer = object : CountDownTimer(timeLeft * 1000, 1000) {
            override fun onTick(l: Long) {
                timeLeft = l
                updateText()
            }
        

            override fun onFinish() {
                isRunning = false
                isResume = false
                isReset = false
                binding.btnStart.text = "Start"
                binding.btnStart.isEnabled = true
                binding.btnReset.isEnabled = false
                binding.timeText.text = "Count is over :)"
            }
        }.start()
        binding.dataText.setText("")
        isRunning = true
        isReset = false
        isResume = false
        binding.btnStart.text = "Pause"
        binding.btnReset.isEnabled = false
    }


    private fun pauseTime() {
        countDownTimer!!.cancel()
        isRunning = false
        isResume = true
        isReset = false
        binding.btnStart.text = "Resume"
        binding.btnReset.isEnabled = true
    }


    private fun resetTime() {
        isResume = false
        isRunning = false
        isReset = true
        timeLeft = data
        binding.timeText.text = "Remaining Second:$data"
        binding.btnReset.isEnabled = false
        binding.btnStart.isEnabled = true
        binding.btnStart.text = "Start"
    }


    private fun updateText() {
        val second = (timeLeft / 1000).toInt()
        binding.timeText.text = "Remaining Second:$second"
    }


    private fun resumeTime() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(l: Long) {
                timeLeft = l
                updateText()
            }


            override fun onFinish() {
                isRunning = false
                isReset = false
                isResume = false
                binding.btnStart.text = "Start"
                binding.btnStart.isEnabled = true
                binding.btnReset.isEnabled = true
                binding.timeText.text = "Count is over :)"
            }
        }.start()
        isRunning = true
        isReset = false
        isResume = false
        binding.btnStart.text = "Pause"
        binding.btnReset.isEnabled = false
    }


    private fun start2Time() {
        countDownTimer = object : CountDownTimer(timeLeft * 1000, 1000) {
            override fun onTick(l: Long) {
                timeLeft = l
                updateText()
            }


            override fun onFinish() {
                isRunning = false
                isReset = false
                isResume = false
                binding.btnStart.text = "Start"
                binding.btnStart.isEnabled = true
                binding.btnReset.isEnabled = false
                binding.timeText.text = "Count is over :)"
            }
        }.start()
        binding.dataText.setText("")
        isRunning = true
        isReset = false
        isResume = false
        binding.btnStart.text = "Pause"
        binding.btnReset.isEnabled = false
    }

    companion object {
        private var timeLeft: Long = 0
        private var data: Long = 0
    }
}