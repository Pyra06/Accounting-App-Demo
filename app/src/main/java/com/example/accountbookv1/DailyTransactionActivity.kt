package com.example.accountbookv1

import android.app.DatePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_daliy_transaction.*
import kotlinx.android.synthetic.main.activity_daliy_transaction.btn_Save
import kotlinx.android.synthetic.main.activity_daliy_transaction.btn_Clear
import kotlinx.android.synthetic.main.activity_daliy_transaction.btn_Delete
import kotlinx.android.synthetic.main.activity_daliy_transaction.btn_New
import kotlinx.android.synthetic.main.activity_daliy_transaction.btn_Update
import kotlinx.android.synthetic.main.activity_daliy_transaction.linearLayoutSelected
import kotlinx.android.synthetic.main.activity_daliy_transaction.linearLayoutAdd
import kotlinx.android.synthetic.main.activity_daliy_transaction.txtID
import java.util.*

class DailyTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daliy_transaction)
        val db = DataBaseHandler(this)
        etvDate.addTextChangedListener(textWatcher)
        etvAmount.addTextChangedListener(textWatcher)
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, IntentFilter("Sending-Data-MDT"))
        refreshSpinner()
        refreshSave()
        dateSelection()

        btn_Calender.setOnClickListener() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener() { _, sYear, sMonth, sDayOfMonth ->
                val date = ("$sDayOfMonth" + "/" + (sMonth.toString().toInt() + 1) + "/" + "$sYear")
                etvDate.setText(date)
            }, year, month, day)

            dialog.show()
        }

        btn_New.setOnClickListener() {
            linearLayoutAdd.visibility = View.VISIBLE
            linearLayoutSelected.visibility = View.INVISIBLE
        }

        btn_Save.setOnClickListener() {
            addAmount()
            refreshSave()
        }

        btn_Reset.setOnClickListener() {
            txtID.text = ""
            acName.text = ""
            etvDate.setText("")
            etvAmount.setText("")
            acName.visibility = View.INVISIBLE
            spnName.visibility = View.VISIBLE
            etvRemark.setText("")
            linearLayoutAdd.visibility = View.INVISIBLE
        }

        btn_Update.setOnClickListener() {
            db.editDataMDT(txtID.text.toString().toInt(),etvAmount.text.toString().toInt(),etvRemark.text.toString())
            refreshSave()
            txtID.text = ""
            acName.text = ""
            etvDate.setText("")
            etvAmount.setText("")
            acName.visibility = View.INVISIBLE
            spnName.visibility = View.VISIBLE
            etvRemark.setText("")
            linearLayoutSelected.visibility = View.INVISIBLE
            refreshSpinner()
        }

        btn_Delete.setOnClickListener() {
            db.deleteDataMDT(txtID.text.toString().toInt())
            refreshSave()
            txtID.text = ""
            acName.text = ""
            etvDate.setText("")
            etvAmount.setText("")
            acName.visibility = View.INVISIBLE
            spnName.visibility = View.VISIBLE
            etvRemark.setText("")
            linearLayoutSelected.visibility = View.INVISIBLE
            refreshSpinner()
        }

        btn_Clear.setOnClickListener() {
            refreshSave()
            txtID.text = ""
            acName.text = ""
            etvDate.setText("")
            etvAmount.setText("")
            acName.visibility = View.INVISIBLE
            spnName.visibility = View.VISIBLE
            etvRemark.setText("")
            linearLayoutSelected.visibility = View.INVISIBLE
            refreshSpinner()
        }
    }

    private fun dateSelection() {
        etvDate.setText(intent.getStringExtra("DateString"))
    }

    private fun refreshSpinner() {
        val db = DataBaseHandler(this)
        spnName.adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_activated_1, db.getAccountList().sortedWith(compareBy{it.name}))
    }

    private fun refreshSave() {
        val db = DataBaseHandler(this)
        val data = db.getUserMDT(this)
        val adapter = MainAdapterMDT(data)
        val rv: RecyclerView = findViewById(R.id.recyclerViewTableMDT)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    private fun addAmount() {
        val db = DataBaseHandler(this)
        val accountName = spnName.selectedItem.toString().lastIndexOf("-").plus(0).let {spnName.selectedItem.toString().substring(0, it)}
        val acctType = spnName.selectedItem.toString().lastIndexOf("(").plus(1).let {spnName.selectedItem.toString().substring(it, spnName.selectedItem.toString().lastIndexOf(")").plus(0))}
        val acctId = db.getAccountID(accountName, acctType).toString()
        val acctIdFnl = acctId.lastIndexOf("[").plus(1).let {acctId.substring(it, acctId.lastIndexOf("]").plus(0))}.toString().toInt()
        val amount = etvAmount.text.toString()
        val note = etvRemark.text.toString()
        val type = spnName.selectedItem.toString().lastIndexOf("(").plus(1).let {spnName.selectedItem.toString().substring(it, spnName.selectedItem.toString().lastIndexOf(")").plus(0))}
        val date = etvDate.text.toString()
        db.insertDataMDT(acctIdFnl, date, note, type, amount)
    }

    private var mMessageReceiver: BroadcastReceiver = object:BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val db = DataBaseHandler(applicationContext)
            val acDetails = db.getAccountDetailsMDT(intent.getStringExtra("ID").toInt()).toString()
            val acNoBrace = acDetails.lastIndexOf("[").plus(1).let {acDetails.substring(it, acDetails.lastIndexOf("]").plus(0))}.toString()
            txtID.text = intent.getStringExtra("TRNS_ID")
            etvDate.setText(intent.getStringExtra("DATE"))
            etvAmount.setText(intent.getStringExtra("AMOUNT"))
            etvRemark.setText(intent.getStringExtra("NOTE"))
            spnName.visibility = View.INVISIBLE
            acName.text = "    $acNoBrace"
            acName.visibility = View.VISIBLE
            linearLayoutSelected.visibility = View.VISIBLE
            linearLayoutAdd.visibility = View.INVISIBLE
        }
    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            btn_Save.isEnabled = etvAmount.text.toString().isNotEmpty() && etvDate.text.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}
