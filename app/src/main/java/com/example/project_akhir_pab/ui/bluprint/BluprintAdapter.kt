package com.example.project_akhir_pab.ui.bluprint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class BluprintAdapter(private val listBluprint: ArrayList<Bluprint>) : RecyclerView.Adapter<BluprintAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaBluprint: TextView = itemView.findViewById(R.id.txt1)
        val btnDetail: TextView = itemView.findViewById(R.id.btnDetail)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickCallback?.onItemClicked(listBluprint[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_list_bluprint, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listBluprint.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (namaBluprint) = listBluprint[position]
        holder.namaBluprint.text = namaBluprint
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Bluprint)
    }

}