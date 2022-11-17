package com.abhay.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvDateSelected : TextView?=null
    private var minutes: TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn=findViewById<Button>(R.id.BtnDate)
        tvDateSelected=findViewById(R.id.tvdate)
        minutes=findViewById(R.id.tvminutes)
        btn.setOnClickListener{
            dateSelectButtonPressed()
        }

    }

   private fun dateSelectButtonPressed(){

        val myCal= Calendar.getInstance()
        val year=myCal.get(Calendar.YEAR)
        val month=myCal.get(Calendar.MONTH)
        val day=myCal.get(Calendar.DAY_OF_MONTH)

        var dpd=// DATE SELECTER DIALOG
            DatePickerDialog(this,

                DatePickerDialog.OnDateSetListener{view,Selectedyear,Selectedmonth,SelecteddayOfMonth ->

                    Toast.makeText(this,
                        " Year was ${Selectedyear}, Month was ${Selectedmonth+1} and Day was ${SelecteddayOfMonth}",
                        Toast.LENGTH_SHORT
                    ).show()

                    val selectedDate="${SelecteddayOfMonth}/${Selectedmonth+1}/${Selectedyear}"
                    tvDateSelected?.text=selectedDate

                    // TO GET AGE IN MINUTES
                    // WE WILL USE SIMPLE DATE FORMAT
                    // CREATING AN OBJECT OF SIMPLE DATE FORMAT

                    var sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val theDate=sdf.parse(selectedDate)

                        val selectedDateInMinutes=theDate.time/60000
                        //time prop. gives us the time in ms that has passed since jan 1 , 1970 midnight
                        // till selected date
                        // divide by 60000 to get in minutes

                        val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                        //Time from 1 jan 1970 in ms to current date

                        val currentdateInMinutes=currentDate.time/60000
                        val diffInMinutes=currentdateInMinutes-selectedDateInMinutes

                        minutes?.text=diffInMinutes.toString()


                },
                year,month,day
            )

        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        // 3.6M ms in 1 hr * 24 hr gives one day time in ms
        dpd.show()

    }



}