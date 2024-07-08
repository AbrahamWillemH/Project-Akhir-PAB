package com.example.project_akhir_pab.ui.prasarana

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_akhir_pab.databinding.FragmentPrasarana1Binding
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class Prasarana1Fragment : Fragment(), Prasarana1Adapter.OnItemClickCallback {

    private var _binding: FragmentPrasarana1Binding? = null
    private val binding get() = _binding!!
    private lateinit var prasaranaList: ArrayList<Prasarana1>
    private lateinit var prasaranaAdapter: Prasarana1Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrasarana1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prasaranaList = ArrayList()
        binding.rvData1.layoutManager = LinearLayoutManager(requireContext())
        prasaranaAdapter = Prasarana1Adapter(prasaranaList)
        binding.rvData1.adapter = prasaranaAdapter

        fetchPrasaranaData()
    }

    private fun fetchPrasaranaData() {
        val urlString = "https://firestore.googleapis.com/v1/projects/asset-pab/databases/(default)/documents/asset/prasarana"
        Thread {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                val response = inputStream.bufferedReader().use { it.readText() }
                parsePrasaranaData(response)
            } else {
                Log.e("Prasarana1Fragment", "Error fetching data")
            }
            connection.disconnect()
        }.start()
    }

    private fun parsePrasaranaData(response: String) {
        try {
            val jsonObject = JSONObject(response)
            val prasaranaArray = jsonObject.getJSONObject("fields").getJSONObject("prasarana").getJSONObject("arrayValue").getJSONArray("values")
            val prasaranaLainArray = jsonObject.getJSONObject("fields").getJSONObject("prasarana_lain").getJSONObject("arrayValue").getJSONArray("values")

            addPrasaranaItems(prasaranaArray)
            addPrasaranaItems(prasaranaLainArray)

            activity?.runOnUiThread {
                prasaranaAdapter.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Log.e("Prasarana1Fragment", "Error parsing data", e)
        }
    }

    private fun addPrasaranaItems(prasaranaArray: JSONArray) {
        for (i in 0 until prasaranaArray.length()) {
            val prasaranaObject = prasaranaArray.getJSONObject(i).getJSONObject("mapValue").getJSONObject("fields")

            if (prasaranaObject.has("jenis")) {
                val jenis = prasaranaObject.getJSONObject("jenis").getString("stringValue")
                val jumlah = if (prasaranaObject.has("jumlah")) prasaranaObject.getJSONObject("jumlah").optInt("integerValue", -1) else -1
                val luas = if (prasaranaObject.has("luas")) prasaranaObject.getJSONObject("luas").optInt("integerValue", -1) else -1
                val kepemilikan = if (prasaranaObject.has("kepemilikan")) prasaranaObject.getJSONObject("kepemilikan").getBoolean("booleanValue") else false
                val kondisi = if (prasaranaObject.has("kondisi")) prasaranaObject.getJSONObject("kondisi").getBoolean("booleanValue") else false

                val milikString = if (kepemilikan) "Sendiri" else "Sewa"
                val kondisiString = if (kondisi) "Terawat" else "Tidak Terawat"
                val luasFormatted = if (luas == -1) {
                    "N/A"
                } else {
                    DecimalFormat("#,###").format(luas)
                }

                val prasarana = Prasarana1(jenis, jumlah, luasFormatted, kepemilikan, kondisi, "$milikString, $kondisiString")
                prasaranaList.add(prasarana)
            }
        }
    }

    override fun onItemClicked(data: Prasarana1) {
        Log.d("Prasarana1Fragment", "Clicked item: $data")
        // Handle item click if needed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
