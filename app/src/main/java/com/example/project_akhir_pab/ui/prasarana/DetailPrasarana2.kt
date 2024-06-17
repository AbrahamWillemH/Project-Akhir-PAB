package com.example.project_akhir_pab.ui.prasarana

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project_akhir_pab.R

class DetailPrasarana2 : Fragment() {
    private lateinit var imgFoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvName2: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvNews: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_prasarana2, container, false)
        imgFoto = view.findViewById(R.id.img_detail_motor_photo)
        tvName = view.findViewById(R.id.tv_detail_motor_name)
        tvName2 = view.findViewById(R.id.tv_detail_motor_harga)
        tvDesc = view.findViewById(R.id.tv_detail_motor_description)
        tvNews = view.findViewById(R.id.tv_detail_motor_news)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val motor = it.getParcelable<Prasarana2>("selected_mobil")
            motor?.let { prasarana ->
                imgFoto.setImageResource(prasarana.img)
                tvName.text = prasarana.name
                tvName2.text = prasarana.name2
                tvDesc.text = prasarana.desc

                val position = it.getInt("selected_position", -1)
                if (position != -1) {
                    val dataMobilNews = resources.getStringArray(R.array.data_motor_news)
                    val additionalInfo = dataMobilNews.getOrNull(position)
                    additionalInfo?.let { info ->
                        tvNews.text = info
                    }
                }
            }
        }
    }
}