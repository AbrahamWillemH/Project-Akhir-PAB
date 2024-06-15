package com.example.project_akhir_pab.ui.prasarana

// Prasarana1Adapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R
<<<<<<< Updated upstream
import com.example.project_akhir_pab.ui.lahan.LahanAdapter

class Prasarana1Adapter(private val prasaranaList: List<Prasarana>) :
    RecyclerView.Adapter<Prasarana1Adapter.PrasaranaViewHolder>() {
    private lateinit var onItemClickCallback: LahanAdapter.OnItemClickCallback
=======
import com.example.project_akhir_pab.ui.prasarana.Prasarana1Adapter

class Prasarana1Adapter(private val listUkm: ArrayList<Prasarana>) : RecyclerView.Adapter<Prasarana1Adapter.ListViewHolder>() {
>>>>>>> Stashed changes

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLahan: TextView = itemView.findViewById(R.id.txt1)
        val tvLuas: TextView = itemView.findViewById(R.id.txt2)
        val btnDetail: TextView = itemView.findViewById(R.id.btnDetail)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickCallback?.onItemClicked(listUkm[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listUkm.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (lokasiLahan, statusKepemilikan, penggunaanLahan, luasLahan) = listUkm[position]
        holder.tvLahan.text = lokasiLahan
        holder.tvLuas.text = luasLahan.toString()
    }
<<<<<<< Updated upstream
    fun setOnItemClickCallback(onItemClickCallback: LahanAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
=======

    interface OnItemClickCallback {
        fun onItemClicked(data: Prasarana)
>>>>>>> Stashed changes
    }

}