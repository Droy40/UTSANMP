package com.example.utsanmp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utsanmp.databinding.DataUkurItemBinding
import com.example.utsanmp.model.DataUkur
import java.util.ArrayList

class DataListAdapter(val dataList:ArrayList<DataUkur>)
    : RecyclerView.Adapter<DataListAdapter.DataViewHolder>(){
    class DataViewHolder(var binding: DataUkurItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataUkurItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.textUsia.text = dataList[position].usia.toString()
        holder.binding.textBeratBadan.text = dataList[position].beratBadan.toString()
        holder.binding.textTinggiBadan.text = dataList[position].tinggiBadan.toString()
    }

    fun updateDataList(newDataList: List<DataUkur>)
    {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }
}