package com.example.accountbookv1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ledger.*
import java.util.*

class Ledger : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ledger)

        textDateFrom.setOnClickListener() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener() { _, sYear, sMonth, sDayOfMonth ->
                val date = ("$sDayOfMonth" + "/" + (sMonth.toString().toInt() + 1) + "/" + "$sYear")
                textDateFrom.setText(date)
            }, year, month, day)

            dialog.show()
        }

        textDateTo.setOnClickListener() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener() { _, sYear, sMonth, sDayOfMonth ->
                val date = ("$sDayOfMonth" + "/" + (sMonth.toString().toInt() + 1) + "/" + "$sYear")
                textDateTo.setText(date)
            }, year, month, day)

            dialog.show()
        }
    }
}
