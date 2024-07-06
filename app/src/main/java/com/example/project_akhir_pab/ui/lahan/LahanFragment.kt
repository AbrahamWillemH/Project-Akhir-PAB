package com.example.project_akhir_pab.ui.lahan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentLahanBinding
import com.google.firebase.firestore.FirebaseFirestore

class LahanFragment : Fragment(), LahanAdapter.OnItemClickCallback {

    private lateinit var rvLahan: RecyclerView
    private lateinit var lahanAdapter: LahanAdapter
    private lateinit var firestore: FirebaseFirestore
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
        rvLahan.layoutManager = LinearLayoutManager(requireContext())
        lahanAdapter = LahanAdapter(emptyList())
        rvLahan.adapter = lahanAdapter

        firestore = FirebaseFirestore.getInstance()
        fetchLahanData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchLahanData() {
        firestore.collection("asset").document("lokasi_kepemilikan_lahan").get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val dataArray = document.get("Lahan") as? List<Map<String, Any>>
                    Log.d("LahanFragment", "Data fetched: $dataArray")
                    val listLahan = dataArray?.map { map ->
                        Lahan(
                            lokasiLahan = map["lokasiLahan"] as? String ?: "N/A",
                            statusKepemilikan = map["statusKepemilikan"] as? String ?: "N/A",
                            penggunaanLahan = map["penggunaanLahan"] as? String ?: "N/A",
                            luasLahan = map["luasLahan"] as? String ?: "N/A",
                            latitude = (map["latitude"] as? Double) ?: 0.0,
                            longitude = (map["longitude"] as? Double) ?: 0.0
                        )
                    }
                    if (listLahan != null) {
                        lahanAdapter = LahanAdapter(listLahan)
                        lahanAdapter.setOnItemClickCallback(this)
                        rvLahan.adapter = lahanAdapter
                    }
                } else {
                    Log.d("LahanFragment", "Document not found")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LahanFragment", "Error getting documents: ", exception)
            }
    }

    override fun onItemClicked(data: Lahan) {
        val bundle = Bundle().apply {
            putParcelable("EXTRA_LAHAN", data)
        }
        findNavController().navigate(R.id.action_nav_lahan_to_detailLahanFragment, bundle)
    }
}
