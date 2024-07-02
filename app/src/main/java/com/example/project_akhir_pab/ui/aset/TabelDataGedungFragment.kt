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

class TabelDataGedungFragment : Fragment() {

    private lateinit var spinnerTahun: Spinner
    private lateinit var buttonPilihTahun: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var gedungAdapter: GedungAdapter
    private lateinit var totalJumlahTextView: TextView
    private lateinit var totalLuasTextView: TextView
    private var gedungList: MutableList<Gedung> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tabel_data_gedung, container, false)
        spinnerTahun = view.findViewById(R.id.spinner_tahun)
        buttonPilihTahun = view.findViewById(R.id.button_pilih_tahun)
        recyclerView = view.findViewById(R.id.recycler_view)
        totalJumlahTextView = view.findViewById(R.id.total_jumlah)
        totalLuasTextView = view.findViewById(R.id.total_luas)

        // Set up spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tahun_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTahun.adapter = adapter
        }

        // Set up RecyclerView
        gedungAdapter = GedungAdapter(gedungList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = gedungAdapter

        // Handle button click
        buttonPilihTahun.setOnClickListener {
            val selectedYear = spinnerTahun.selectedItem.toString()
            loadDataForYear(selectedYear)
        }

        return view
    }

    // Function to load data into the RecyclerView
    private fun loadDataForYear(year: String) {
        // Clear previous data
        gedungList.clear()

        // Retrieve the data from strings.xml
        val gedungData = resources.getStringArray(R.array.gedung_data)
            .map { it.split(",") }
            .filter { it.size >= 5 && it[0] == year }

        // Variables to store the totals
        var totalJumlah = 0
        var totalLuas = 0.0

        // Add items to the list
        gedungData.forEach { item ->
            val gedung = Gedung(
                year = item[0],
                section = item[1],
                description = item[2],
                total = item[3],
                luas = item[4]
            )
            gedungList.add(gedung)

            // Accumulate the totals
            totalJumlah += item[3].toInt()
            totalLuas += item[4].toDouble()
        }

        // Update the adapter
        gedungAdapter.notifyDataSetChanged()

        // Display the totals
        totalJumlahTextView.text = totalJumlah.toString()
        totalLuasTextView.text = String.format("%.3f", totalLuas)
    }
}
