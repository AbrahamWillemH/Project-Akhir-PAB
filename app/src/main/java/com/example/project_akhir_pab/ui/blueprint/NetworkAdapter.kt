package com.example.project_akhir_pab.ui.blueprint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R


class NetworkAdapter (
    private val context: Context,
    private val data: ArrayList<NetworkResponse>
): RecyclerView.Adapter<NetworkAdapter.NetworkViewHolder>() {
    class NetworkViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val alatNetwork = view.findViewById<TextView>(R.id.alat_network)
        val fungsiNetwork = view.findViewById<TextView>(R.id.fungsi_network)
        val jumlahNetwork = view.findViewById<TextView>(R.id.jumlah_network)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.card2_blueprint, parent, false)

        return NetworkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NetworkViewHolder, position: Int) {
        holder.alatNetwork.text = data.get(position).alat
        holder.fungsiNetwork.text = data.get(position).fungsi
        holder.jumlahNetwork.text = data.get(position).jumlah

    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: ArrayList<NetworkResponse>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}