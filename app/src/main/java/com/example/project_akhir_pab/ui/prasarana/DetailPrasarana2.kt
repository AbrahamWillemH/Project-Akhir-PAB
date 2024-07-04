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
import com.google.firebase.firestore.FirebaseFirestore

class DetailPrasarana2 : Fragment() {
    private lateinit var imgFoto: ImageView
    private lateinit var tvJenis: TextView
    private lateinit var tvSumberDana: TextView
    private lateinit var tvRencanaInvestasi: TextView
    private lateinit var tvInvestasiTahap3: TextView

    private val db = FirebaseFirestore.getInstance()

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

        arguments?.let {
            val prasaranaId = it.getString("prasarana_id", "")
            prasaranaId?.let { id ->
                db.collection("prasarana_tambahan")
                    .document(id)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val prasarana = document.toObject(Prasarana2::class.java)
                            prasarana?.let { data ->
                                tvJenis.text = data.jenisPrasarana
                                tvSumberDana.text = data.sumberDana
                                tvRencanaInvestasi.text = data.rencanaInvestasi
                                tvInvestasiTahap3.text = data.investasiTahap3
                                Glide.with(requireContext())
                                    .load(data.photoUrl)
                                    .into(imgFoto)
                            }
                        } else {
                            // Document does not exist
                        }
                    }
                    .addOnFailureListener { exception ->
                        // Handle any errors
                    }
            }
        }
    }
}
