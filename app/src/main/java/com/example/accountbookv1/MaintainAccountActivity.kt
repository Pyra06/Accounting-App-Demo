package com.example.accountbookv1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_maintain_account.*

class MaintainAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintain_account)

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, IntentFilter("Sending-Data-MA"))
        radioGroupType.check(incomeCheck.id)
        val db = DataBaseHandler(this)
        refreshSave()

        btn_New.setOnClickListener(){
            linearLayoutAdd.visibility = View.VISIBLE
            linearLayoutSelected.visibility = View.INVISIBLE
        }

        btn_Save.setOnClickListener {
            val radioCheck = findViewById<RadioButton>(radioGroupType.checkedRadioButtonId)

            if (etvName.text.toString().isNotEmpty()) {
                db.insertDataMA(etvName.text.toString(), radioCheck.text.toString())
            } else {
                Toast.makeText(this, "PLEASE FILL THE FIELD FIRST", Toast.LENGTH_SHORT).show()
            }
            refreshSave()
        }

        btn_Reset.setOnClickListener() {
            txtID.text = ""
            etvName.setText("")
            radioGroupType.check(incomeCheck.id)
            linearLayoutAdd.visibility = View.INVISIBLE
        }

        btn_Update.setOnClickListener() {
            val radioCheck = findViewById<RadioButton>(radioGroupType.checkedRadioButtonId)
            db.editDataMA(txtID.text.toString().toInt(), etvName.text.toString(), radioCheck.text.toString())
            refreshSave()
            txtID.text = ""
            etvName.setText("")
            radioGroupType.check(incomeCheck.id)
            linearLayoutSelected.visibility = View.INVISIBLE
        }

        btn_Delete.setOnClickListener() {
            db.deleteDataMA(txtID.text.toString().toInt())
            refreshSave()
            txtID.text = ""
            etvName.setText("")
            radioGroupType.check(incomeCheck.id)
            linearLayoutSelected.visibility = View.INVISIBLE
        }

        btn_Clear.setOnClickListener() {
            txtID.text = ""
            etvName.setText("")
            radioGroupType.check(incomeCheck.id)
            linearLayoutSelected.visibility = View.INVISIBLE
        }
    }

    private var mMessageReceiver:BroadcastReceiver = object:BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            txtID.text = intent.getStringExtra("ID")
            etvName.setText(intent.getStringExtra("NAME"))

            if (intent.getStringExtra("TYPE") == "Income") {
                radioGroupType.check(incomeCheck.id)
            } else if (intent.getStringExtra("TYPE") == "Expense"){
                radioGroupType.check(expenseCheck.id)
            }

            if (txtID.text.toString().isNotEmpty()) {
                linearLayoutSelected.visibility = View.VISIBLE
            }

            linearLayoutAdd.visibility = View.INVISIBLE
        }
    }

    private fun refreshSave() {
        val db = DataBaseHandler(this)
        val data = db.getUserMA(this)
        val adapter = MainAdapterMA(data)
        recyclerViewTableMA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
        recyclerViewTableMA.adapter = adapter
    }

    override fun onResume() {
        refreshSave()
        super.onResume()
    }
}
