package com.example.utsanmp.viewmodel

import android.app.Application
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.utsanmp.model.DataUkur
import com.example.utsanmp.util.FileHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UkurViewModel(app: Application): AndroidViewModel(app) {
    val dataUkurLD = MutableLiveData<DataUkur>()
        fun saving(dataUkur: DataUkur){
            val filehelper = FileHelper(getApplication())
            val sType = object : TypeToken<List<DataUkur>>() { }.type
            val fromFile = filehelper.readFromFile()
            Log.d("print_file", fromFile)

            //bikin arrayList kosong
            var dataList = mutableListOf<DataUkur>()

            try {
                //convert data json to ArrayList
                dataList = Gson().fromJson<List<DataUkur>>(fromFile, sType).toMutableList()
            }catch (e:Exception){

            }

            //add new parameter or new data to array lis
            dataList.add(dataUkur)

            //convert data to json
            val jsonstring = Gson().toJson(dataList)

            filehelper.writeToFile(jsonstring)
            Log.d("print_file", jsonstring)
        }
}