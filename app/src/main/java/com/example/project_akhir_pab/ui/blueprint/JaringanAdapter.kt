package com.example.project_akhir_pab.ui.blueprint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class JaringanAdapter (
    private val context: Context,
    private val data: ArrayList<JaringanResponse>
): RecyclerView.Adapter<JaringanAdapter.JaringanViewHolder>() {
    class JaringanViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val namaJaringan = view.findViewById<TextView>(R.id.nama_jaringan)
        val statusJaringan = view.findViewById<TextView>(R.id.status_jaringan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JaringanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.card_blueprint, parent, false)

        return JaringanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JaringanViewHolder, position: Int) {
        holder.namaJaringan.text = data.get(position).nama
        holder.statusJaringan.text = data.get(position).status

    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: ArrayList<JaringanResponse>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}