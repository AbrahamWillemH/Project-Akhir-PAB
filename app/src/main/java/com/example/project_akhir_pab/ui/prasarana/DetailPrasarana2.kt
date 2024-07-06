package com.example.project_akhir_pab.ui.prasarana

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.project_akhir_pab.R
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class DetailPrasarana2 : Fragment() {
    private lateinit var imgFoto: ImageView
    private lateinit var tvJenis: TextView
    private lateinit var tvSumberDana: TextView
    private lateinit var tvRencanaInvestasi: TextView
    private lateinit var tvInvestasiTahap3: TextView
    private lateinit var tvLokasi: MapView

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
        tvLokasi = view.findViewById(R.id.tv_lokasi)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Prasarana2>("EXTRA_PRASARANA2")?.let { prasarana ->
            Log.d("DetailPrasarana2", "Received data: ${prasarana.latitude}, ${prasarana.longitude}")

            tvJenis.text = prasarana.jenisPrasarana
            tvSumberDana.text = prasarana.sumberDana
            tvRencanaInvestasi.text = prasarana.rencanaInvestasi
            tvInvestasiTahap3.text = prasarana.investasiTahap3
            Glide.with(requireContext())
                .load(prasarana.photoUrl)
                .placeholder(R.drawable.uns)
                .into(imgFoto)

            // Set ActionBar title
            (activity as? AppCompatActivity)?.supportActionBar?.apply {
                title = prasarana.jenisPrasarana
                setDisplayHomeAsUpEnabled(true)
            }

            // Initialize MapView
            Configuration.getInstance().userAgentValue = requireContext().packageName
            tvLokasi.setMultiTouchControls(true)

            // Set initial map position
            val mapController = tvLokasi.controller
            mapController.setZoom(18.0)
            val startPoint = GeoPoint(prasarana.latitude, prasarana.longitude)
            mapController.setCenter(startPoint)

            // Add marker
            val marker = Marker(tvLokasi)
            marker.position = startPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            tvLokasi.overlays.add(marker)

            // Set marker click event to open Google Maps
            marker.setOnMarkerClickListener { _, _ ->
                val gmmIntentUri = Uri.parse("geo:${prasarana.latitude},${prasarana.longitude}?q=${prasarana.latitude},${prasarana.longitude}(${prasarana.jenisPrasarana})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
                true
            }
        } ?: run {
            Log.e("DetailPrasarana2", "Prasarana data is null")
        }
    }
}
