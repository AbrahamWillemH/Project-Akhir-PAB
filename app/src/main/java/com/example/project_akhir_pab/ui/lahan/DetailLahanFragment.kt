package com.example.project_akhir_pab.ui.lahan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_akhir_pab.databinding.FragmentDetailLahanBinding

class DetailLahanFragment : Fragment() {

    private var _binding: FragmentDetailLahanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailLahanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lahan = arguments?.getParcelable<Lahan>("EXTRA_LAHAN")

        if (lahan != null) {
            binding.txtLokasiLahan.text = lahan.lokasiLahan
            binding.txtStatusKepemilikan.text = lahan.statusKepemilikan
            binding.txtPenggunaanLahan.text = lahan.penggunaanLahan
            binding.txtLuasLahan.text = lahan.luasLahan

            // Mengatur judul ActionBar dengan lokasi lahan
            (activity as? AppCompatActivity)?.supportActionBar?.apply {
                title = lahan.lokasiLahan
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
