package com.example.savvyweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout.make
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import java.util.*

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val zipCodeEditText: EditText = findViewById(R.id.zipCodeEditText)
        val enterButton : Button = findViewById(R.id.enterButton)

        enterButton.setOnClickListener {

            val zipCode: String = zipCodeEditText.text.toString()

            if(zipCode.length != 5){
                Toast.makeText(this, "Please enter valid code", Toast.LENGTH_SHORT).show()
            }else{
               forecastRepository.loadForecast(zipCode)
            }
        }

        //Creating an observer
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //Update adapter
            Toast.makeText(this, "Loaded items", Toast.LENGTH_SHORT).show()
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)


    }
}