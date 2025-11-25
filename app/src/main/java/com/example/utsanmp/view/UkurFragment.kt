package com.example.utsanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.utsanmp.databinding.FragmentUkurBinding
import com.example.utsanmp.model.DataUkur
import com.example.utsanmp.viewmodel.UkurViewModel
import com.google.android.material.snackbar.Snackbar

class UkurFragment : Fragment() {
    private lateinit var binding: FragmentUkurBinding
    private lateinit var viewmodel: UkurViewModel

    private lateinit var dataUkur: DataUkur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUkurBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(UkurViewModel::class.java)
        observeViewModel()

        binding.btnTambahData.setOnClickListener {
            val beratBadan = binding.txtBeratBadan.text.toString().toIntOrNull() ?: 0
            val tinggiBadan = binding.txtBeratBadan.text.toString().toIntOrNull() ?: 0
            val usia = binding.txtUsia.text.toString().toIntOrNull() ?: 0
            /*val newData = DataUkur(beratBadan, tinggiBadan, usia)
            viewmodel.dataUkurLD.value = newData
            viewmodel.save()
            Toast.makeText(requireContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            viewmodel.clear()*/
            var dataUkur = DataUkur(beratBadan,tinggiBadan,usia)
            val list = listOf(dataUkur)
            viewmodel.save(list)
            Snackbar.make(it, "Data created", Snackbar.LENGTH_SHORT).show()
            viewmodel.clear()
            it.findNavController().popBackStack()

        }
    }

    fun observeViewModel() {
        viewmodel.dataUkurLD.observe(viewLifecycleOwner, Observer {
            dataUkur = it
            binding.txtBeratBadan.setText(dataUkur.beratBadan?.toString() ?: "")
            binding.txtTinggiBadan.setText(dataUkur.tinggiBadan?.toString() ?: "")
            binding.txtUsia.setText(dataUkur.usia?.toString() ?: "")
        })
    }
}