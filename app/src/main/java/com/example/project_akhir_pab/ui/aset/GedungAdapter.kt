package com.example.project_akhir_pab.ui.aset

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class GedungAdapter(private val gedungList: List<Gedung>) : RecyclerView.Adapter<GedungAdapter.GedungViewHolder>() {

    class GedungViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val noTextView: TextView = view.findViewById(R.id.noTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val totalTextView: TextView = view.findViewById(R.id.totalTextView)
        val luasTextView: TextView = view.findViewById(R.id.luasTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GedungViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gedung, parent, false)
        return GedungViewHolder(view)
    }

    override fun onBindViewHolder(holder: GedungViewHolder, position: Int) {
        val gedung = gedungList[position]
        holder.noTextView.text = (position + 1).toString() // Display the item number
        holder.descriptionTextView.text = gedung.description
        holder.totalTextView.text = gedung.total
        holder.luasTextView.text = gedung.luas
    }

    override fun getItemCount() = gedungList.size
}
