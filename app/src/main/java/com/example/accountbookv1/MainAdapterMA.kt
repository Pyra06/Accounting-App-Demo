package com.example.accountbookv1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.table_row_ma.view.*

class MainAdapterMA(private val accounts : ArrayList<UserMA>): RecyclerView.Adapter<MainAdapterMA.CustomViewHolder>() {

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val idList: TextView = view.textIDList
        val nameList: TextView = view.textNameList
        val typeList: TextView = view.textTypeList

        init {
            view.setOnClickListener {
                val intent = Intent("Sending-Data-MA")

                intent.putExtra("ID", view.textIDList.text.toString())
                intent.putExtra("NAME", view.textNameList.text.toString())
                intent.putExtra("TYPE", view.textTypeList.text.toString())
                LocalBroadcastManager.getInstance(view.context).sendBroadcast(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.table_row_ma, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val account : UserMA = accounts[position]
        holder.idList.text = account.id.toString()
        holder.nameList.text = account.name
        holder.typeList.text = account.type
    }
}
