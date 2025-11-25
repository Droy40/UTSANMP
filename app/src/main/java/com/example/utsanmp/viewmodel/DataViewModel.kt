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
        // readFromFile may throw or return null/empty; guard against that
        val content = try {
            fileHelper.readFromFile()
        } catch (e: Exception) {
            Log.e("DataViewModel", "Failed to read file", e)
            null
        }

        if (content.isNullOrBlank()) {
            // nothing to parse or file missing/empty -> expose empty list and log path
            Log.w("DataViewModel", "File is empty or missing: ${fileHelper.getFilePath()}")
            dataLD.value = ArrayList()
            return
        }

        val sType = object : TypeToken<List<DataUkur>>() { }.type
        try {
            val result: List<DataUkur>? = Gson().fromJson(content, sType)
            dataLD.value = if (result != null) ArrayList(result) else ArrayList()
            Log.d("print_file", content)
            Log.d("print_file", fileHelper.getFilePath())
        } catch (e: Exception) {
            // malformed JSON -> log and provide empty list so downstream UI doesn't crash
            Log.e("DataViewModel", "Failed to parse JSON content", e)
            dataLD.value = ArrayList()
        }
    }
}