package com.example.accountbookv1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.table_row_mdt.view.*

class MainAdapterMDT(private val accounts : ArrayList<UserMDT>): RecyclerView.Adapter<MainAdapterMDT.CustomViewHolder>() {

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val trnsIDList: TextView = view.textIDList
        val dateList: TextView = view.textDateList
        val amountList: TextView = view.textAmountList
        val typeList: TextView = view.textTypeList
        var idList: Int = 0
        var noteList: String = ""

        init {
            view.setOnClickListener {
                val intent = Intent("Sending-Data-MDT")

                intent.putExtra("TRNS_ID", view.textIDList.text.toString())
                intent.putExtra("DATE", view.textDateList.text.toString())
                intent.putExtra("AMOUNT", view.textAmountList.text.toString())
                intent.putExtra("TYPE", view.textTypeList.text.toString())
                intent.putExtra("ID", idList.toString())
                intent.putExtra("NOTE", noteList)
                LocalBroadcastManager.getInstance(view.context).sendBroadcast(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.table_row_mdt, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val account : UserMDT = accounts[position]
        holder.trnsIDList.text = account.trnsID.toString()
        holder.dateList.text = account.date
        holder.amountList.text = account.amount.toString()
        holder.typeList.text = account.type
        holder.idList = account.id.toString().toInt()
        holder.noteList = account.note
    }
}