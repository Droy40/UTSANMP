package com.example.utsanmp.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.utsanmp.R
import com.example.utsanmp.databinding.FragmentUkurBinding
import com.example.utsanmp.model.DataUkur
import com.example.utsanmp.viewmodel.UkurViewModel

class UkurFragment : Fragment() {
    private lateinit var binding: FragmentUkurBinding
    private lateinit var viewmodel: UkurViewModel

    private lateinit var dataUkur : DataUkur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUkurBinding.inflate(inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this).get(UkurViewModel::class.java)

        binding.btnTambahData.setOnClickListener {
            val beratBadan = binding.txtBeratBadan.text.toString().toIntOrNull() ?: 0
            val tinggiBadan = binding.txtTinggiBadan.text.toString().toIntOrNull() ?: 0
            val usia = binding.txtUsia.text.toString().toIntOrNull() ?: 0

            viewmodel.saving(DataUkur(beratBadan, tinggiBadan, usia))
            observeViewModel()

        }
    }

    fun observeViewModel(){
        viewmodel.dataUkurLD.observe(viewLifecycleOwner, Observer {
            dataUkur = it

            //update UI
            binding.txtBeratBadan.setText(dataUkur.beratBadan.toString())
            binding.txtTinggiBadan.setText(dataUkur.tinggiBadan.toString())
            binding.txtUsia.setText(dataUkur.usia.toString())

        })
    }

}