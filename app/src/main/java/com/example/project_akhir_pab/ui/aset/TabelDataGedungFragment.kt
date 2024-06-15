package com.example.project_akhir_pab.ui.aset

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.project_akhir_pab.R

class TabelDataGedungFragment : Fragment() {

    private lateinit var spinnerTahun: Spinner
    private lateinit var buttonPilihTahun: Button
    private lateinit var tableLayout: TableLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tabel_data_gedung, container, false)
        spinnerTahun = view.findViewById(R.id.spinner_tahun)
        buttonPilihTahun = view.findViewById(R.id.button_pilih_tahun)
        tableLayout = view.findViewById(R.id.table_layout)

        // Set up spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tahun_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTahun.adapter = adapter
        }

        // Handle button click
        buttonPilihTahun.setOnClickListener {
            val selectedYear = spinnerTahun.selectedItem.toString()
            loadDataForYear(selectedYear)
        }

        return view
    }

    // Function to load data into the table
    private fun loadDataForYear(year: String) {
        // Clear previous data
        tableLayout.removeViews(1, tableLayout.childCount - 1)

        val gedungList = resources.getStringArray(R.array.gedung_data)
            .map { it.split(",") }
            .map { Gedung(it[0], it[1], it[2], it[3]) }
            .filter { it.no == year }

        for (gedung in gedungList) {
            val tableRow = TableRow(context)

            val noTextView = TextView(context).apply {
                text = gedung.no
                setPadding(8, 8, 8, 8)
                gravity = Gravity.CENTER
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            }

            val descriptionTextView = TextView(context).apply {
                text = gedung.description
                setPadding(8, 8, 8, 8)
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f)
                setSingleLine(false)
                setMaxLines(3)
                gravity = Gravity.START
            }

            val jumlahTextView = TextView(context).apply {
                text = gedung.total
                setPadding(8, 8, 8, 8)
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                gravity = Gravity.CENTER
            }

            val luasTextView = TextView(context).apply {
                text = gedung.luas
                setPadding(8, 8, 8, 8)
                layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                gravity = Gravity.CENTER
            }

            tableRow.addView(noTextView)
            tableRow.addView(descriptionTextView)
            tableRow.addView(jumlahTextView)
            tableRow.addView(luasTextView)

            tableLayout.addView(tableRow)
        }
    }
}