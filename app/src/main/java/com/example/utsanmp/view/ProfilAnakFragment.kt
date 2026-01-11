package com.example.utsanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.example.utsanmp.viewmodel.ProfilAnakViewModel
import java.util.Calendar
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import com.example.utsanmp.databinding.FragmentProfilAnakBinding
import java.util.Locale
import com.example.utsanmp.model.Profile

class ProfilAnakFragment : Fragment() , ProfileListener{

    private lateinit var binding: FragmentProfilAnakBinding
    private lateinit var viewModel: ProfilAnakViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilAnakBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfilAnakViewModel::class.java]
        viewModel.refresh()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.profileListener = this

        binding.txtDob.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireContext(), { _: DatePicker, y: Int, m: Int, d: Int ->
                val monthOneBased = m + 1
                val formatted = String.format(Locale.getDefault(), "%04d-%02d-%02d", y, monthOneBased, d)
                viewModel.profileLD.value?.tanggalLahir = formatted
                viewModel.profileLD.value = viewModel.profileLD.value
            }, year, month, day)
            dpd.show()
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.profileLD.observe(viewLifecycleOwner,{ profile ->
            if (profile == null) {
                viewModel.profileLD.value = Profile("", "", "")
            }
        })
    }

    override fun onSaveClick() {
        val name = viewModel.profileLD.value?.nama?.trim() ?: ""
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.saveProfile()
        Toast.makeText(requireContext(), "Profil berhasil disimpan", Toast.LENGTH_SHORT).show()
    }
}