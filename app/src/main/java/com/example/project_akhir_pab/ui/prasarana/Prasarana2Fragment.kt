package com.example.project_akhir_pab.ui.prasarana

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentPrasarana2Binding
import com.google.firebase.firestore.FirebaseFirestore

class Prasarana2Fragment : Fragment(), ListPrasarana2Adapter.OnItemClickListener {

    private var _binding: FragmentPrasarana2Binding? = null
    private val binding get() = _binding!!
    private lateinit var prasaranaList: ArrayList<Prasarana2>
    private lateinit var prasaranaAdapter: ListPrasarana2Adapter
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrasarana2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prasaranaList = ArrayList()
        firestore = FirebaseFirestore.getInstance()

        binding.rvData.layoutManager = LinearLayoutManager(requireContext())
        prasaranaAdapter = ListPrasarana2Adapter(prasaranaList, this)
        binding.rvData.adapter = prasaranaAdapter

        fetchPrasaranaData()
    }

    private fun fetchPrasaranaData() {
        firestore.collection("asset").document("prasarana_tambahan").get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val dataArray = document.get("data") as? List<Map<String, Any>>
                    Log.d("Prasarana2Fragment", "Data fetched: $dataArray")
                    if (dataArray != null) {
                        for (dataMap in dataArray) {
                            val prasarana = Prasarana2(
                                jenisPrasarana = dataMap["Jenis"] as? String ?: "N/A",
                                sumberDana = dataMap["s_dana"] as? String ?: "N/A",
                                rencanaInvestasi = convertToString(dataMap["rencaran_inv"]),
                                investasiTahap3 = convertToString(dataMap["inv_3"]),
                                photoUrl = dataMap["gambar"] as? String ?: "",
                                latitude = (dataMap["latitude"] as? Double) ?: -7.558861637127492,
                                longitude = (dataMap["longitude"] as? Double) ?: 110.8565092460276
                            )
                            Log.d("Prasarana2Fragment", "Parsed prasarana: $prasarana")
                            prasaranaList.add(prasarana)
                        }
                        prasaranaAdapter.notifyDataSetChanged()
                    } else {
                        Log.d("Prasarana2Fragment", "null")
                    }
                } else {
                    Log.d("Prasarana2Fragment", "null")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Prasarana2Fragment", "null", exception)
            }
    }



    override fun onItemClicked(prasarana: Prasarana2) {
        Log.d("Prasarana2Fragment", "Passing data: $prasarana")
        val bundle = Bundle().apply {
            putParcelable("EXTRA_PRASARANA2", prasarana)
        }
        findNavController().navigate(R.id.action_nav_prasarana2_to_detailPrasarana2, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun convertToString(value: Any?): String {
        return when (value) {
            is Double, is Long -> value.toString()
            else -> "N/A"
        }
    }
}
