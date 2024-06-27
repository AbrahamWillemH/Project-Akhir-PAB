package com.example.project_akhir_pab.ui.lahan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentDetailLahanBinding
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

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

            // Inisialisasi MapView
            Configuration.getInstance().userAgentValue = requireContext().packageName
            val map = binding.root.findViewById<MapView>(R.id.map)
            map.setMultiTouchControls(true)

            // Atur posisi peta awal
            val mapController = map.controller
            mapController.setZoom(15.0)
            val startPoint = GeoPoint(lahan.latitude, lahan.longitude)
            mapController.setCenter(startPoint)

            // Tambahkan marker
            val marker = Marker(map)
            marker.position = startPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            map.overlays.add(marker)

            // Atur event klik marker untuk membuka Google Maps
            marker.setOnMarkerClickListener { _, _ ->
                val gmmIntentUri =
                    Uri.parse("geo:${lahan.latitude},${lahan.longitude}?q=${lahan.latitude},${lahan.longitude}(${lahan.lokasiLahan})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
