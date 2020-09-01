package com.otero.qrcodereader.ui.readList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otero.qrcodereader.R
import com.otero.qrcodereader.extensions.getCurrentTimestamp
import com.otero.qrcodereader.model.QrCodeInfoModel
import java.util.*

class ResultAdapter(var qrCodeInfoModels: List<QrCodeInfoModel> = listOf()) :
    RecyclerView.Adapter<ItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return qrCodeInfoModels.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(qrCodeInfoModels[position])
    }
}

class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var value: TextView = itemView.findViewById(R.id.value)
    private var date: TextView = itemView.findViewById(R.id.date)

    fun bind(itemResult: QrCodeInfoModel) {
        value.text = itemResult.value
        date.text = Date(itemResult.timeStamp).getCurrentTimestamp()
    }
}
