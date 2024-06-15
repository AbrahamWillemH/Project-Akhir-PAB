package com.l0122017.alvito.projectakhir.ui.lahan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class LahanAdapter(private val listLahan: ArrayList<Lahan>) : RecyclerView.Adapter<LahanAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLahan: TextView = itemView.findViewById(R.id.txt1)
        val tvLuas: TextView = itemView.findViewById(R.id.txt2)
        val btnDetail: TextView = itemView.findViewById(R.id.btnDetail)

        fun bind(lahan: Lahan) {
            tvLahan.text = lahan.lokasiLahan
            tvLuas.text = lahan.luasLahan

            btnDetail.setOnClickListener {
                onItemClickCallback.onItemClicked(lahan)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listLahan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val lahan = listLahan[position]
        holder.bind(lahan)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Lahan)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}
