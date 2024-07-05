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

class NetworkFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NetworkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_network, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerViewNetworkBlueprint)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NetworkAdapter(requireContext(), ArrayList())
        recyclerView.adapter = adapter

        fetchNetworkData()

        return rootView
    }

    private fun fetchNetworkData() {
        val call: Call<List<NetworkResponse>> = ApiClient.networkApiService.getNetwork()

        call.enqueue(object : Callback<List<NetworkResponse>> {
            override fun onResponse(
                call: Call<List<NetworkResponse>>,
                response: Response<List<NetworkResponse>>
            ) {
                if (response.isSuccessful) {
                    val networkList = response.body()
                    networkList?.let {
                        adapter.setData(ArrayList(it))
                    }
                }
            }

            override fun onFailure(call: Call<List<NetworkResponse>>, t: Throwable) {
                // Handle failure
                t.printStackTrace()
            }
        })
    }
}