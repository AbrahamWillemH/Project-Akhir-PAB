package com.example.project_akhir_pab.ui.aset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_akhir_pab.R

class AsetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_aset, container, false)

        // Find the button and set up the listener
        val buttonLihatGedung: Button = view.findViewById(R.id.button_lihat)
        buttonLihatGedung.setOnClickListener {
            findNavController().navigate(R.id.action_asetFragment_to_tabelDataGedungFragment)
        }

        val buttonLihatTanah: Button = view.findViewById(R.id.button_lihat2)
        buttonLihatTanah.setOnClickListener {
            findNavController().navigate(R.id.action_asetFragment_to_tabelDataTanahFragment)
        }

        return view
    }
}
