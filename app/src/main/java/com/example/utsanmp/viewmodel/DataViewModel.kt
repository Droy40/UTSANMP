package com.example.utsanmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.utsanmp.model.DataUkur
import com.example.utsanmp.util.FileHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataViewModel(app: Application) : AndroidViewModel(app) {

    val dataLD = MutableLiveData<ArrayList<DataUkur>>()

    fun refresh(){
        val fileHelper = FileHelper(getApplication())
        val content = fileHelper.readFromFile()
        val sType = object : TypeToken<List<DataUkur>>() { }.type
        val result = Gson().fromJson<List<DataUkur>>(content, sType)
        dataLD.value = result as ArrayList<DataUkur>
        Log.d("print_file", content)
        Log.d("print_file", fileHelper.getFilePath())
    }
}