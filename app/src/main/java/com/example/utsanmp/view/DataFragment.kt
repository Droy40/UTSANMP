package com.example.utsanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsanmp.databinding.FragmentDataBinding
import com.example.utsanmp.viewmodel.DataViewModel

class DataFragment : Fragment(){
    private lateinit var binding: FragmentDataBinding
    private lateinit var viewModel: DataViewModel //declare view model nya
    private val dataListAdapter = DataListAdapter(arrayListOf())

    companion object {
        fun newInstance() = DataFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        viewModel.refresh()

        //--set-up recycle view
        binding.recData.layoutManager = LinearLayoutManager(context)
        binding.recData.adapter = dataListAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.dataLD_List.observe(viewLifecycleOwner, Observer {
            dataListAdapter.updateDataList(it)
        })
    }
}