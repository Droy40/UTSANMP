package com.example.utsanmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.utsanmp.model.DataUkur
import com.example.utsanmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UkurViewModel(app: Application)
    : AndroidViewModel(app), CoroutineScope {
    val dataUkurLD = MutableLiveData<DataUkur>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    init { clear() }

    /*fun save() {
        val filehelper = FileHelper(getApplication())
        val sType = object : TypeToken<MutableList<DataUkur>>() {}.type
        val fromFile = filehelper.readFromFile()

        // parse existing list safely
        val dataList: MutableList<DataUkur> = try {
            if (fromFile.isBlank()) mutableListOf()
            else {
                Gson().fromJson(fromFile, sType) ?: mutableListOf()
            }
        } catch (e: Exception) {
            Log.w("UkurViewModel", "Failed to parse existing data file, recreating list", e)
            mutableListOf()
        }
        // get current value and add if not null
        val current = dataUkurLD.value
        if (current == null) {
            Log.w("UkurViewModel", "No data in dataUkurLD to save")
            return
        }

        dataList.add(current)
        //convert data to json and persist
        try {
            val jsonstring = Gson().toJson(dataList)
            filehelper.writeToFile(jsonstring)
        } catch (e: Exception) {
            Log.e("UkurViewModel", "Failed to write data file", e)
        }
    }*/

    fun save(list:List<DataUkur>){
        launch{
            val db = buildDb(getApplication())
            db.dataUkurDao().insertAll(*list.toTypedArray())
        }
    }

    fun clear(){
        dataUkurLD.value = DataUkur(null,null,null)
    }


}