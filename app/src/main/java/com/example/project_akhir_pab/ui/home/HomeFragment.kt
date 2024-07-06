package com.example.project_akhir_pab.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_akhir_pab.R
import com.example.project_akhir_pab.databinding.FragmentHomeBinding
import com.example.project_akhir_pab.ui.aset.AsetFragment
import com.example.project_akhir_pab.ui.blueprint.BlueprintFragment
import com.example.project_akhir_pab.ui.lahan.LahanFragment
import com.example.project_akhir_pab.ui.prasarana.Prasarana2Fragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set OnClickListener for each LinearLayout in GridLayout
        binding.image1.setOnClickListener {
            navigateToFragment(AsetFragment())
        }

        binding.image2.setOnClickListener {
            navigateToFragment(BlueprintFragment())
        }

//        binding.image3.setOnClickListener {
//            navigateToFragment(Prasarana1Fragment())
//        }

        binding.image4.setOnClickListener {
            navigateToFragment(Prasarana2Fragment())
        }

        binding.image5.setOnClickListener {
            navigateToFragment(LahanFragment())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
