package com.example.project_akhir_pab.ui.prasarana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class Prasarana1Adapter(private val listPrasarana: ArrayList<Prasarana1>) : RecyclerView.Adapter<Prasarana1Adapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJenis: TextView = itemView.findViewById(R.id.tvJenis)
        val tvJumlah: TextView = itemView.findViewById(R.id.tvJumlah)
        val tvLuas: TextView = itemView.findViewById(R.id.tvLuas)
        val tvKepemilikan: TextView = itemView.findViewById(R.id.tvKepemilikan)
        val tvKondisi: TextView = itemView.findViewById(R.id.tvKondisi)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickCallback?.onItemClicked(listPrasarana[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_prasarana1, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPrasarana.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val prasarana = listPrasarana[position]
        holder.tvJenis.text = prasarana.jenis.ifEmpty { "N/A" }
        holder.tvJumlah.text = prasarana.jumlah.toString().ifEmpty { "N/A" }
        holder.tvLuas.text = prasarana.luas.toString().ifEmpty { "N/A" }
        holder.tvKepemilikan.text = if (prasarana.milik) "Sendiri" else "Sewa"
        holder.tvKondisi.text = if (prasarana.kondisi) "Terawat" else "Tidak Terawat"
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Prasarana1)
    }
}
