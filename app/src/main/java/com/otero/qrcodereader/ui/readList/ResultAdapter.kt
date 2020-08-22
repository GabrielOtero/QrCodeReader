package com.otero.qrcodereader.ui.readList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otero.qrcodereader.R

class ResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

}


class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

}
