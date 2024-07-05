package com.example.project_akhir_pab.ui.aset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R
import com.google.firebase.firestore.FirebaseFirestore

class TabelDataTanahFragment : Fragment() {

    private lateinit var spinnerTahun: Spinner
    private lateinit var buttonPilihTahun: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var tanahAdapter: TanahAdapter
    private lateinit var totalJumlahTextView: TextView
    private lateinit var totalLuasTextView: TextView
    private var tanahList: MutableList<Tanah> = mutableListOf()
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tabel_data_tanah, container, false)
        spinnerTahun = view.findViewById(R.id.spinner_tahun)
        buttonPilihTahun = view.findViewById(R.id.button_pilih_tahun)
        recyclerView = view.findViewById(R.id.recycler_view)
        totalJumlahTextView = view.findViewById(R.id.total_jumlah)
        totalLuasTextView = view.findViewById(R.id.total_luas)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tahun_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTahun.adapter = adapter
        }

        tanahAdapter = TanahAdapter(tanahList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = tanahAdapter

        buttonPilihTahun.setOnClickListener {
            val selectedYear = spinnerTahun.selectedItem.toString()
            loadDataForYear(selectedYear)
        }
        db = FirebaseFirestore.getInstance()

        return view
    }

    private fun loadDataForYear(year: String) {
        // Clear previous data
        tanahList.clear()

        // Fetch data from Firestore
        db.collection("asset").document("aset2").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val yearData = document.get(year) as? List<Map<String, Any>>
                    yearData?.forEach { section ->
                        section.forEach { (location, items) ->
                            val itemList = items as? List<Map<String, Any>>
                            itemList?.forEach { item ->
                                val description = item["Uraian"] as? String ?: ""
                                val total = item["Jumlah"] as? Number ?: 0
                                val luas = item["Luas"] as? Number ?: 0

                                val tanah = Tanah(
                                    year = year,
                                    section = location,
                                    description = description,
                                    total = total.toString(),
                                    luas = luas.toString()
                                )
                                tanahList.add(tanah)
                            }
                        }
                    }
                    tanahAdapter.notifyDataSetChanged()

                    val totalJumlah = tanahList.sumOf { it.total.toInt() }
                    val totalLuas = tanahList.sumOf { it.luas.toDouble() }

                    totalJumlahTextView.text = totalJumlah.toString()
                    totalLuasTextView.text = String.format("%.1f", totalLuas)

                    totalJumlahTextView.visibility = View.VISIBLE
                    totalLuasTextView.visibility = View.VISIBLE
                }
            }
    }
}