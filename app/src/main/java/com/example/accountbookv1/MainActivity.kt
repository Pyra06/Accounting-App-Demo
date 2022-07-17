package com.example.accountbookv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_MA.setOnClickListener() {
            val intent = Intent(this, MaintainAccountActivity::class.java)
            startActivity(intent)
        }

        btn_MDT.setOnClickListener() {
            val intent = Intent(this, DailyTransactionActivity::class.java)
            startActivity(intent)
        }

        btn_MR.setOnClickListener() {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
    }
}
