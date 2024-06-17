package com.example.project_akhir_pab.ui.prasarana

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_akhir_pab.R

class Prasarana2Fragment : Fragment() {

    private lateinit var rvData: RecyclerView
    private lateinit var list: ArrayList<Prasarana2>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prasarana2, container, false)
        rvData = view.findViewById(R.id.rv_motor)
        rvData.setHasFixedSize(true)

        list = getListData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRecyclerList()
    }

    private fun getListData(): ArrayList<Prasarana2> {
        val dataName = resources.getStringArray(R.array.data_motor_nama)
        val dataName2 = resources.getStringArray(R.array.data_motor_harga)
        val dataDesc = resources.getStringArray(R.array.data_motor_desc)
        val dataImg = resources.obtainTypedArray(R.array.data_motor_img)
        val listData = ArrayList<Prasarana2>()
        for (i in dataName.indices) {
            val motor = Prasarana2(dataName[i], dataName2[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listData.add(motor)
        }
        dataImg.recycle()
        return listData
    }

    private fun showRecyclerList() {
        rvData.layoutManager = LinearLayoutManager(requireContext())
        val listDataAdapter = ListPrasarana2Adapter(list)
        rvData.adapter = listDataAdapter

        listDataAdapter.setOnItemClickCallback(object : ListPrasarana2Adapter.OnItemClickMotorback {
            override fun onItemClicked(data: Prasarana2) {
                showSelectedData(data)
            }
        })
    }

    private fun showSelectedData(motor: Prasarana2) {
        val position = list.indexOf(motor)

        val bundle = Bundle().apply {
            putParcelable("selected_mobil", motor)
            putInt("selected_position", position)
        }

        findNavController().navigate(R.id.action_nav_prasarana2_to_detailPrasarana2, bundle)
    }
}
