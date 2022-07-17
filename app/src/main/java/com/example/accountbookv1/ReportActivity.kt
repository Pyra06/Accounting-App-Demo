package com.example.accountbookv1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        btn_acList.setOnClickListener() {
            val intent = Intent(this, AccountList::class.java)
            startActivity(intent)
        }

        btn_Ledger.setOnClickListener() {
            val intent = Intent(this, Ledger::class.java)
            startActivity(intent)
        }
    }
}
