package com.example.project_akhir_pab.ui.blueprint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JaringanFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JaringanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_jaringan, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerViewJaringanBlueprint)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = JaringanAdapter(requireContext(), ArrayList())
        recyclerView.adapter = adapter

        fetchJaringanData()

        return rootView
    }

    private fun fetchJaringanData() {
        val call: Call<List<JaringanResponse>> = ApiClient.jaringanApiService.getJaringan()

        call.enqueue(object : Callback<List<JaringanResponse>> {
            override fun onResponse(
                call: Call<List<JaringanResponse>>,
                response: Response<List<JaringanResponse>>
            ) {
                if (response.isSuccessful) {
                    val jaringanList = response.body()
                    jaringanList?.let {
                        adapter.setData(ArrayList(it))
                    }
                }
            }

            override fun onFailure(call: Call<List<JaringanResponse>>, t: Throwable) {
                // Handle failure
                t.printStackTrace()
            }
        })
    }
}