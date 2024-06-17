package com.example.project_akhir_pab.ui.prasarana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class ListPrasarana2Adapter(private val listmotor : ArrayList<Prasarana2>) : RecyclerView.Adapter<ListPrasarana2Adapter.ListViewHolder>() {

    private lateinit var onItemClickMotorback: OnItemClickMotorback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto : ImageView = itemView.findViewById(R.id.img_item_motor_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_motor_name)
        val tvName2 : TextView = itemView.findViewById(R.id.tv_item_motor_harga)
        val tvdesc : TextView = itemView.findViewById(R.id.tv_item_motor_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_prasarana2, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listmotor.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val informasi = listmotor[position]
        holder.imgFoto.setImageResource(informasi.img)
        holder.tvName.text = informasi.name
        holder.tvName2.text = informasi.name2
        holder.tvdesc.text = informasi.desc

        holder.itemView.setOnClickListener {
            onItemClickMotorback.onItemClicked((listmotor[holder.adapterPosition]))
        }
    }

    interface OnItemClickMotorback {
        fun onItemClicked(data: Prasarana2)
    }

    fun setOnItemClickCallback(onItemClickSurahback: OnItemClickMotorback) {
        this.onItemClickMotorback = onItemClickSurahback
    }
}