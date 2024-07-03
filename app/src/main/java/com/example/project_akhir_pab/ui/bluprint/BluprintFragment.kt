package com.example.project_akhir_pab.ui.bluprint

//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.project_akhir_pab.R
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class BluprintFragment<ApiService> : Fragment() {
//
//    private lateinit var rvBluprint: RecyclerView
//    private lateinit var listBluprint: ArrayList<Bluprint>
//    private lateinit var apiService: ApiService // Impor ApiService tanpa masalah jika sudah di-impor dengan benar
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_bluprint, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        rvBluprint = view.findViewById(R.id.recyclerViewBluprint)
//        rvBluprint.layoutManager = LinearLayoutManager(requireContext())
//
//        // Inisialisasi Retrofit
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://your-api-url/") // Ganti dengan URL API Anda
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)
//
//        // Memanggil fungsi untuk mengambil data dari API
//        fetchBluprints()
//    }
//
//    private fun fetchBluprints() {
//        apiService.getBluprints().enqueue(object : Callback<List<Bluprint>> {
//            override fun onResponse(
//                call: Call<List<Bluprint>>,
//                response: Response<List<Bluprint>>
//            ) {
//                if (response.isSuccessful) {
//                    val bluprints = response.body()
//                    if (bluprints != null) {
//                        listBluprint = ArrayList(bluprints)
//                        showRecyclerList()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Bluprint>>, t: Throwable) {
//                // Handle error
//            }
//        })
//    }
//
//    private fun showRecyclerList() {
//        val listBluprintAdapter = BluprintAdapter(listBluprint)
//        rvBluprint.adapter = listBluprintAdapter
//    }
//}





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
        val deskripsiBluprint = resources.getStringArray(R.array.deskripsi_bluprint)

        val listBluprint = ArrayList<Bluprint>()
        for (i in dataBluprint.indices) {
            val bluprint = Bluprint(dataBluprint[i], deskripsiBluprint[i])
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
