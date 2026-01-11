package com.example.utsanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.utsanmp.databinding.FragmentUkurBinding
import com.example.utsanmp.viewmodel.UkurViewModel
import com.google.android.material.snackbar.Snackbar

class UkurFragment : Fragment(), UkurListener {
    private lateinit var binding: FragmentUkurBinding
    private lateinit var viewModel: UkurViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUkurBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UkurViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.listener = this
    }

    override fun onTambahDataClick() {
        val data = viewModel.dataUkurLD.value
        if (data?.beratBadan == null || data.beratBadan == 0) {
            Toast.makeText(requireContext(), "Berat Badan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if (data.tinggiBadan == null || data.tinggiBadan == 0) {
            Toast.makeText(requireContext(), "Tinggi Badan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        if (data.usia == null || data.usia == 0) {
            Toast.makeText(requireContext(), "Usia tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.save()
        Snackbar.make(binding.root, "Data berhasil disimpan", Snackbar.LENGTH_SHORT).show()
        viewModel.clear()
        findNavController().popBackStack()
    }
}