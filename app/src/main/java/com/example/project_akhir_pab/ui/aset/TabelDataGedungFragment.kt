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

        // Retrieve the data from strings.xml
        val gedungData = resources.getStringArray(R.array.gedung_data)
            .map { it.split(",") }
            .filter { it.size >= 5 && it[0] == year }

        // Group the data by section
        val groupedData = gedungData.groupBy { it[1] }

        // Variables to store the totals
        var totalJumlah = 0
        var totalLuas = 0.0

        // Iterate over each section and add the header and its items to the table
        groupedData.forEach { (section, items) ->
            // Add section header row
            val sectionRow = TableRow(context)
            val sectionTextView = TextView(context).apply {
                text = section
                setPadding(8, 8, 8, 8)
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                gravity = Gravity.START
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            sectionRow.addView(sectionTextView)
            tableLayout.addView(sectionRow)

            // Add items for this section
            for ((index, item) in items.withIndex()) {
                val tableRow = TableRow(context)

                val noTextView = TextView(context).apply {
                    text = (index + 1).toString()
                    setPadding(8, 8, 8, 8)
                    gravity = Gravity.CENTER
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                }

                val descriptionTextView = TextView(context).apply {
                    text = item[2]
                    setPadding(8, 8, 8, 8)
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f)
                    setSingleLine(false)
                    setMaxLines(3)
                    gravity = Gravity.START
                }

                val jumlahTextView = TextView(context).apply {
                    text = item[3]
                    setPadding(8, 8, 8, 8)
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.CENTER
                }

                val luasTextView = TextView(context).apply {
                    text = item[4]
                    setPadding(8, 8, 8, 8)
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    gravity = Gravity.CENTER
                }

                tableRow.addView(noTextView)
                tableRow.addView(descriptionTextView)
                tableRow.addView(jumlahTextView)
                tableRow.addView(luasTextView)

                tableLayout.addView(tableRow)

                // Accumulate the totals
                totalJumlah += item[3].toInt()
                totalLuas += item[4].toDouble()
            }
        }

        // Add Jumlah section at the bottom
        val jumlahRow = TableRow(context)
        val jumlahTextView = TextView(context).apply {
            text = "Jumlah"
            setPadding(8, 8, 8, 8)
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.START
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        jumlahRow.addView(jumlahTextView)

        val emptyTextView = TextView(context).apply {
            setPadding(8, 8, 8, 8)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3f)
        }
        jumlahRow.addView(emptyTextView)

        val totalJumlahTextView = TextView(context).apply {
            text = totalJumlah.toString()
            setPadding(8, 8, 8, 8)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.START
        }
        jumlahRow.addView(totalJumlahTextView)

        val totalLuasTextView = TextView(context).apply {
            text = String.format("%.3f", totalLuas)
            setPadding(8, 8, 8, 8)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.START
        }
        jumlahRow.addView(totalLuasTextView)

        tableLayout.addView(jumlahRow)
    }
}
