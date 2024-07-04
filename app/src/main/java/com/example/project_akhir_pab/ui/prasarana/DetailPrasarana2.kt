package com.example.project_akhir_pab.ui.prasarana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.project_akhir_pab.R

class DetailPrasarana2 : Fragment() {
    private lateinit var imgFoto: ImageView
    private lateinit var tvJenis: TextView
    private lateinit var tvSumberDana: TextView
    private lateinit var tvRencanaInvestasi: TextView
    private lateinit var tvInvestasiTahap3: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_prasarana2, container, false)
        imgFoto = view.findViewById(R.id.img_detail_prasarana_photo)
        tvJenis = view.findViewById(R.id.tv_detail_prasarana_jenis)
        tvSumberDana = view.findViewById(R.id.tv_detail_prasarana_sumber_dana)
        tvRencanaInvestasi = view.findViewById(R.id.tv_detail_prasarana_rencana_inv)
        tvInvestasiTahap3 = view.findViewById(R.id.tv_detail_prasarana_inv3)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Prasarana2>("EXTRA_PRASARANA2")?.let { prasarana ->
            tvJenis.text = prasarana.jenisPrasarana
            tvSumberDana.text = prasarana.sumberDana
            tvRencanaInvestasi.text = prasarana.rencanaInvestasi
            tvInvestasiTahap3.text = prasarana.investasiTahap3
            Glide.with(requireContext())
                .load(prasarana.photoUrl)
                .into(imgFoto)
        }
    }
}
