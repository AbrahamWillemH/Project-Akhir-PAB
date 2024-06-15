package com.example.project_akhir_pab.ui.prasarana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentPrasarana1Binding

class Prasarana1Fragment : Fragment(), Prasarana1Adapter.OnItemClickCallback {

    private lateinit var rvPrasarana1: RecyclerView
    private lateinit var listPrasarana1: ArrayList<Prasarana>
    private var _binding: FragmentPrasarana1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrasarana1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPrasarana1 = binding.recyclerViewLokasiKepemilikanPrasarana1
        rvPrasarana1.setHasFixedSize(true)

        listPrasarana1 = ArrayList()
        listPrasarana1.addAll(getListPrasarana1())
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListPrasarana1(): List<Prasarana> {
        val dataNama = resources.getStringArray(R.array.data_nama_prasarana)
        val dataJenis = resources.getStringArray(R.array.data_jenis_prasarana)
        val dataDeskripsi = resources.getStringArray(R.array.data_deskripsi_prasarana)
        val dataJumlah = resources.getStringArray(R.array.data_jumlah_unit)

        val listPrasarana1 = ArrayList<Prasarana>()
        for (i in dataNama.indices) {
            val prasarana1 = Prasarana(dataNama[i], dataJenis[i], dataDeskripsi[i], dataJumlah[i])
            listPrasarana1.add(prasarana1)
        }
        return listPrasarana1
    }

    private fun showRecyclerList() {
        rvPrasarana1.layoutManager = LinearLayoutManager(requireContext())
        val listPrasarana1Adapter = Prasarana1Adapter(listPrasarana1)
        listPrasarana1Adapter.setOnItemClickCallback(this)
        rvPrasarana1.adapter = listPrasarana1Adapter
    }

    override fun onItemClicked(data: Prasarana) {
        val bundle = Bundle().apply {
            putParcelable("EXTRA_PRASARANA1", data)
        }
        findNavController().navigate(R.id.action_nav_prasarana1_to_detailPrasarana1Fragment, bundle)
    }
}
