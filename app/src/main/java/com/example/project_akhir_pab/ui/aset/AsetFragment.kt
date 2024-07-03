package com.example.project_akhir_pab.ui.aset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_akhir_pab.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.firestore.FirebaseFirestore

class AsetFragment : Fragment() {

    private lateinit var barChartGedung: BarChart
    private lateinit var barChartTanah: BarChart
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_aset, container, false)

        val buttonLihatGedung: Button = view.findViewById(R.id.button_lihat)
        buttonLihatGedung.setOnClickListener {
            findNavController().navigate(R.id.action_asetFragment_to_tabelDataGedungFragment)
        }

        val buttonLihatTanah: Button = view.findViewById(R.id.button_lihat2)
        buttonLihatTanah.setOnClickListener {
            findNavController().navigate(R.id.action_asetFragment_to_tabelDataTanahFragment)
        }

        barChartGedung = view.findViewById(R.id.gedungchart)
        barChartTanah = view.findViewById(R.id.tanahchart)
        db = FirebaseFirestore.getInstance()
        setupBarChartGedung()
        setupBarChartTanah()

        return view
    }

    private fun setupBarChartGedung() {
        db.collection("asset").document("aset").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val entries = ArrayList<BarEntry>()
                    var index = 1f
                    document.data?.toSortedMap()?.forEach { (year, value) ->
                        val yearData = value as? List<Map<String, Any>>
                        var totalJumlah = 0
                        yearData?.forEach { section ->
                            section.forEach { (_, items) ->
                                val itemList = items as? List<Map<String, Any>>
                                itemList?.forEach { item ->
                                    val jumlah = item["Jumlah"] as? Number ?: 0
                                    totalJumlah += jumlah.toInt()
                                }
                            }
                        }
                        entries.add(BarEntry(index, totalJumlah.toFloat()))
                        index += 1f
                    }

                    val dataSet = BarDataSet(entries, "Jumlah Gedung per Tahun")
                    dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
                    val data = BarData(dataSet)
                    barChartGedung.data = data

                    // Disable the description label
                    barChartGedung.description.isEnabled = false

                    barChartGedung.invalidate()
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors here
            }
    }

    private fun setupBarChartTanah() {
        db.collection("asset").document("aset2").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val entries = ArrayList<BarEntry>()
                    var index = 1f
                    document.data?.toSortedMap()?.forEach { (year, value) ->
                        val yearData = value as? List<Map<String, Any>>
                        var totalLuas = 0.0
                        yearData?.forEach { section ->
                            section.forEach { (_, items) ->
                                val itemList = items as? List<Map<String, Any>>
                                itemList?.forEach { item ->
                                    val luas = item["Luas"] as? Number ?: 0.0
                                    totalLuas += luas.toDouble()
                                }
                            }
                        }
                        entries.add(BarEntry(index, totalLuas.toFloat()))
                        index += 1f
                    }

                    val dataSet = BarDataSet(entries, "Luas Tanah per Tahun")
                    dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
                    val data = BarData(dataSet)
                    barChartTanah.data = data

                    // Disable the description label
                    barChartTanah.description.isEnabled = false

                    barChartTanah.invalidate()
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors here
            }
    }
}
