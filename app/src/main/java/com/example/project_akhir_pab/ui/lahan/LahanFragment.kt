package com.example.project_akhir_pab.ui.lahan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l0122017.alvito.projectakhir.ui.lahan.Lahan
import com.l0122017.alvito.projectakhir.ui.lahan.LahanAdapter
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentLahanBinding

class LahanFragment : Fragment(), LahanAdapter.OnItemClickCallback {

    private lateinit var rvLahan: RecyclerView
    private lateinit var listLahan: ArrayList<Lahan>
    private var _binding: FragmentLahanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLahanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLahan = binding.recyclerViewLokasiKepemilikanLahan
        rvLahan.setHasFixedSize(true)

        listLahan = ArrayList()
        listLahan.addAll(getListLahan())
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListLahan(): List<Lahan> {
        val dataLokasi = resources.getStringArray(R.array.data_lokasi)
        val dataStatus = resources.getStringArray(R.array.data_status)
        val dataPenggunaan = resources.getStringArray(R.array.data_penggunaan)
        val dataLuas = resources.getStringArray(R.array.data_luas)

        val listLahan = ArrayList<Lahan>()
        for (i in dataLokasi.indices) {
            val lahan = Lahan(dataLokasi[i], dataStatus[i], dataPenggunaan[i], dataLuas[i])
            listLahan.add(lahan)
        }
        return listLahan
    }

    private fun showRecyclerList() {
        rvLahan.layoutManager = LinearLayoutManager(requireContext())
        val listLahanAdapter = LahanAdapter(listLahan)
        listLahanAdapter.setOnItemClickCallback(this)
        rvLahan.adapter = listLahanAdapter
    }

    override fun onItemClicked(data: Lahan) {
        val bundle = Bundle().apply {
            putParcelable("EXTRA_LAHAN", data)
        }
        findNavController().navigate(R.id.action_nav_lahan_to_detailLahanFragment, bundle)
    }
}
