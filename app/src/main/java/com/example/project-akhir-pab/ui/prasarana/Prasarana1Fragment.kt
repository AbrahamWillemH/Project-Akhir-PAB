package com.example.`project-akhir-pab`.ui.prasarana

// Prasarana1Fragment.kt
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.`project-akhir-pab`.R
import com.example.`project-akhir-pab`.databinding.FragmentPrasarana1Binding

class Prasarana1Fragment : Fragment(), Prasarana1Adapter.OnItemClickCallback {

    private lateinit var rvPrasarana: RecyclerView
    private lateinit var listPrasarana: ArrayList<Prasarana>
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

        rvPrasarana = binding.recyclerViewPrasarana1
        rvPrasarana.setHasFixedSize(true)

        listPrasarana = ArrayList()
        listPrasarana.addAll(getListPrasarana())
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListPrasarana(): List<Prasarana> {
        // Contoh data, sesuaikan dengan sumber data asli Anda
        val dataNama = arrayOf("Prasarana 1", "Prasarana 2", "Prasarana 3")
        val dataJenis = arrayOf("Jenis 1", "Jenis 2", "Jenis 3")

        val listPrasarana = ArrayList<Prasarana>()
        for (i in dataNama.indices) {
            val prasarana = Prasarana(dataNama[i], dataJenis[i])
            listPrasarana.add(prasarana)
        }
        return listPrasarana
    }

    private fun showRecyclerList() {
        rvPrasarana.layoutManager = LinearLayoutManager(requireContext())
        val listPrasaranaAdapter = Prasarana1Adapter(listPrasarana)
        listPrasaranaAdapter.setOnItemClickCallback(this)
        rvPrasarana.adapter = listPrasaranaAdapter
    }

    override fun onItemClicked(data: Prasarana) {
        val bundle = Bundle().apply {
            putParcelable("EXTRA_PRASARANA", data)
        }
        findNavController().navigate(R.id.action_nav_prasarana1_to_detailPrasaranaFragment, bundle)
    }
}
