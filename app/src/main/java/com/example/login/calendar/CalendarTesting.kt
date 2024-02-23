package com.example.login.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import com.testing.login.R
import com.testing.login.databinding.ActivityCalendarTestingBinding
import com.testing.login.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CalendarTesting : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarTestingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarTestingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridOverlay = binding.gridOverlay
        val blockedDates = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DAY_OF_MONTH, 15)
        }

        addBlockedTimeSlot(blockedDates)


        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            showToast("working")
        }
    }

    private fun addBlockedTimeSlot(blockedDate: Calendar) {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(blockedDate.time)
        val textView = TextView(this).apply {
            text = "Blocked: $formattedDate"
            gravity = Gravity.CENTER
            setBackgroundColor(resources.getColor(R.color.white))
        }

        val row = blockedDate.get(Calendar.WEEK_OF_MONTH) - 1
        val column = blockedDate.get(Calendar.DAY_OF_WEEK) - 1

        val params = GridLayout.LayoutParams().apply {
            rowSpec = GridLayout.spec(row)
            columnSpec = GridLayout.spec(column)
        }
        binding.gridOverlay.addView(textView, params)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}