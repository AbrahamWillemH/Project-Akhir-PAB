package com.example.project_akhir_pab.ui.aset

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class TanahAdapter(private val tanahList: List<Tanah>) : RecyclerView.Adapter<TanahAdapter.TanahViewHolder>() {

    class TanahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noTextView: TextView = view.findViewById(R.id.noTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val totalTextView: TextView = view.findViewById(R.id.totalTextView)
        val luasTextView: TextView = view.findViewById(R.id.luasTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanahViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gedung, parent, false)
        return TanahViewHolder(view)
    }

    override fun onBindViewHolder(holder: TanahViewHolder, position: Int) {
        val tanah = tanahList[position]
        holder.noTextView.text = (position + 1).toString() // Display the item number
        holder.descriptionTextView.text = tanah.description
        holder.totalTextView.text = tanah.total
        holder.luasTextView.text = tanah.luas
    }

    override fun getItemCount() = tanahList.size
}