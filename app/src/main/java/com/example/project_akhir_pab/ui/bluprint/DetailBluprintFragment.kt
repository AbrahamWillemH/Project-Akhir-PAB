package com.example.project_akhir_pab.ui.bluprint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.project_akhir_pab.databinding.FragmentDetailBluprintBinding


class DetailBluprintFragment : Fragment() {
    private var _binding: FragmentDetailBluprintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBluprintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blueprint = arguments?.getParcelable<Bluprint>("EXTRA_BLUPRINT")

        if (blueprint != null) {
            binding.tvDetailNamaBluprint.text = blueprint.namaBluprint
//            binding.tvDetailDeskripsiBluprint.text = blueprint.deskripsiBlueprint

            // Mengatur judul ActionBar dengan nama blueprint
            (activity as? AppCompatActivity)?.supportActionBar?.apply {
                title = blueprint.namaBluprint
                setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}