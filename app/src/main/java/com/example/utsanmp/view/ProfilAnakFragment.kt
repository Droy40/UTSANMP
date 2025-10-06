package com.example.utsanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.example.utsanmp.R
import com.example.utsanmp.viewmodel.ProfilAnakViewModel
import java.util.Calendar
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import com.example.utsanmp.databinding.FragmentProfilAnakBinding
import java.util.Locale

class ProfilAnakFragment : Fragment() {

    private lateinit var binding: FragmentProfilAnakBinding
    private lateinit var viewModel: ProfilAnakViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        binding.txtDob.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(requireContext(), { _: DatePicker, y: Int, m: Int, d: Int ->
                val monthOneBased = m + 1
                val formatted = String.format(Locale.getDefault(), "%04d-%02d-%02d", y, monthOneBased, d)
                binding.txtDob.setText(formatted)
            }, year, month, day)
            dpd.show()
        }

        binding.btnSave.setOnClickListener {
            val name = binding.txtName.text?.toString()?.trim() ?: ""
            val dob = binding.txtDob.text?.toString()?.trim() ?: ""
            val selectedId = binding.rgGender.checkedRadioButtonId
            val gender = when (selectedId) {
                R.id.rbMale -> "Laki-laki"
                R.id.rbFemale -> "Perempuan"
                else -> ""
            }

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.saveProfile(name, dob, gender)
            Toast.makeText(requireContext(), "Profil berhasil disimpan", Toast.LENGTH_SHORT).show()
        }
        obserbeViewModel()
    }

    fun obserbeViewModel(){
        viewModel.nameLD.observe(viewLifecycleOwner,{
            binding.txtName.setText(it)
        })
        viewModel.dobLD.observe(viewLifecycleOwner,{
            binding.txtDob.setText(it)
        })
        viewModel.genderLD.observe(viewLifecycleOwner,{
            when (it) {
                "Laki-laki" -> binding.rbMale.isChecked = true
                "Perempuan" -> binding.rbFemale.isChecked = true
            }
        })
    }
}