package com.example.accountbookv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.table_row_ma.view.*

class MainAdapterMRA(private val accounts : List<UserMRA>): RecyclerView.Adapter<MainAdapterMRA.CustomViewHolder>()  {

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val idList: TextView = view.textIDList
        val nameList: TextView = view.textNameList
        val typeList: TextView = view.textTypeList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.table_row_ma, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val account : UserMRA = accounts[position]
        holder.idList.text = account.id.toString()
        holder.nameList.text = account.name
        holder.typeList.text = account.type
    }

}