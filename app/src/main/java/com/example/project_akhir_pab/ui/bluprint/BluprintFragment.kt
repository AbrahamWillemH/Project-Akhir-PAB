package com.example.project_akhir_pab.ui.bluprint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentBluprintBinding



class BluprintFragment : Fragment(), BluprintAdapter.OnItemClickCallback {

    private lateinit var rvBluprint: RecyclerView
    private lateinit var listBluprint: ArrayList<Bluprint>
    private var _binding: FragmentBluprintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBluprintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvBluprint = binding.recyclerViewBluprint
        rvBluprint.setHasFixedSize(true)

        listBluprint = ArrayList()
        listBluprint.addAll(getListBluprint())
        showRecyclerList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getListBluprint(): List<Bluprint> {
        val dataBluprint = resources.getStringArray(R.array.data_bluprint)

        val listBluprint = ArrayList<Bluprint>()
        for (i in dataBluprint.indices) {
            val bluprint = Bluprint(dataBluprint[i])
            listBluprint.add(bluprint)
        }
        return listBluprint
    }

    private fun showRecyclerList() {
        rvBluprint.layoutManager = LinearLayoutManager(requireContext())
        val listBluprintAdapter = BluprintAdapter(listBluprint)
        listBluprintAdapter.setOnItemClickCallback(this)
        rvBluprint.adapter = listBluprintAdapter
    }

    override fun onItemClicked(data: Bluprint) {
        val bundle = Bundle().apply {
            putParcelable("EXTRA_BLUPRINT", data)
        }
        findNavController().navigate(R.id.action_nav_bluprint_to_detailBluprintFragment, bundle)
    }
}
