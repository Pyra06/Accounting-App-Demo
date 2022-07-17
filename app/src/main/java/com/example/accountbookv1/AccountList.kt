package com.example.accountbookv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_account_list.*


class AccountList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_list)
        radioGroupSort.check(rb_ID.id)

        button2.setOnClickListener() {
            refreshSave()
            radioGroupType.clearCheck()
        }
    }

    private fun refreshSave() {
        val db = DataBaseHandler(this)

        if (textIDFrom.text.toString().isNotEmpty() && textIDTo.text.toString().isNotEmpty()) {
            val radioCheck1 = findViewById<RadioButton>(radioGroupType.checkedRadioButtonId)
            val radioCheck2 = findViewById<RadioButton>(radioGroupSort.checkedRadioButtonId)

            val xyz = if (radioGroupType.checkedRadioButtonId == -1) {
                ""
            } else {
                radioCheck1.text.toString()
            }

            when {
                radioCheck2.text.toString() == "ID" -> {
                    val data = db.getAccountDetailsMACase1(textIDFrom.text.toString().toInt(),textIDTo.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.id})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "NAME" -> {
                    val data = db.getAccountDetailsMACase1(textIDFrom.text.toString().toInt(),textIDTo.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.name})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "TYPE" -> {
                    val data = db.getAccountDetailsMACase1(textIDFrom.text.toString().toInt(),textIDTo.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.type})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
            }
        } else if (textIDFrom.text.toString().isEmpty() && textIDTo.text.toString().isEmpty()) {
            val radioCheck1 = findViewById<RadioButton>(radioGroupType.checkedRadioButtonId)
            val radioCheck2 = findViewById<RadioButton>(radioGroupSort.checkedRadioButtonId)

            val xyz = if (radioGroupType.checkedRadioButtonId == -1) {
                ""
            } else {
                radioCheck1.text.toString()
            }

            when {
                radioCheck2.text.toString() == "ID" -> {
                    val data = db.getAccountDetailsMACase3(etvName.text.toString(), xyz).sortedWith(compareBy{it.id})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "NAME" -> {
                    val data = db.getAccountDetailsMACase3(etvName.text.toString(), xyz).sortedWith(compareBy{it.name})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "TYPE" -> {
                    val data = db.getAccountDetailsMACase3(etvName.text.toString(), xyz).sortedWith(compareBy{it.type})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
            }
        } else if (textIDFrom.text.toString().isNotEmpty() && textIDTo.text.toString().isEmpty()) {
            val radioCheck1 = findViewById<RadioButton>(radioGroupType.checkedRadioButtonId)
            val radioCheck2 = findViewById<RadioButton>(radioGroupSort.checkedRadioButtonId)

            val xyz = if (radioGroupType.checkedRadioButtonId == -1) {
                ""
            } else {
                radioCheck1.text.toString()
            }

            when {
                radioCheck2.text.toString() == "ID" -> {
                    val data = db.getAccountDetailsMACase2(textIDFrom.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.id})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "NAME" -> {
                    val data = db.getAccountDetailsMACase2(textIDFrom.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.name})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "TYPE" -> {
                    val data = db.getAccountDetailsMACase2(textIDFrom.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.type})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
            }
        } else if (textIDTo.text.toString().isNotEmpty() && textIDFrom.text.toString().isEmpty()) {
            val radioCheck1 = findViewById<RadioButton>(radioGroupType.checkedRadioButtonId)
            val radioCheck2 = findViewById<RadioButton>(radioGroupSort.checkedRadioButtonId)

            val xyz = if (radioGroupType.checkedRadioButtonId == -1) {
                ""
            } else {
                radioCheck1.text.toString()
            }

            when {
                radioCheck2.text.toString() == "ID" -> {
                    val data = db.getAccountDetailsMACase2(textIDTo.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.id})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "NAME" -> {
                    val data = db.getAccountDetailsMACase2(textIDTo.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.name})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
                radioCheck2.text.toString() == "TYPE" -> {
                    val data = db.getAccountDetailsMACase2(textIDTo.text.toString().toInt(),etvName.text.toString(), xyz).sortedWith(compareBy{it.type})
                    val adapter = MainAdapterMRA(data)
                    recyclerViewTableMRA.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
                    recyclerViewTableMRA.adapter = adapter
                }
            }
        }
    }
}
