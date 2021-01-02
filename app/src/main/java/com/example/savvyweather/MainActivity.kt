package com.example.savvyweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout.make
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val forecastList: RecyclerView = findViewById(R.id.recycler_view)
        forecastList.layoutManager = LinearLayoutManager(this)

        val dailyForecastAdapter = DailyForecastAdapter(){forecast ->
            val message = getString(R.string.forecast_clicked_format, forecast.temp, forecast.description)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForecastAdapter

        //Creating an observer
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            //Update adapter
           dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)


    }
}