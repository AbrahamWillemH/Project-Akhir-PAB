package com.example.project_akhir_pab.ui.prasarana

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_akhir_pab.databinding.ItemRowPrasarana2Binding

class ListPrasarana2Adapter(private val prasaranaList: List<Prasarana2>) :
    RecyclerView.Adapter<ListPrasarana2Adapter.PrasaranaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrasaranaViewHolder {
        val binding = ItemRowPrasarana2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrasaranaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrasaranaViewHolder, position: Int) {
        Log.d("ListPrasarana2Adapter", "Binding data at position $position: ${prasaranaList[position]}")
        holder.bind(prasaranaList[position])
    }

    override fun getItemCount(): Int = prasaranaList.size

    inner class PrasaranaViewHolder(private val binding: ItemRowPrasarana2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(prasarana: Prasarana2) {
            binding.tvDetailPrasaranaJenis.text = prasarana.jenisPrasarana
            binding.tvDetailPrasaranaSumberDana.text = prasarana.sumberDana
            binding.tvDetailPrasaranaRencanaInv.text = prasarana.rencanaInvestasi
            binding.tvDetailPrasaranaInv3.text = prasarana.investasiTahap3
            Glide.with(binding.root.context).load(prasarana.photoUrl).into(binding.imgDetailPrasaranaPhoto)
        }
    }
}
