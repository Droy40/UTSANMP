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

class DataViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {

    val dataLD_List = MutableLiveData<List<DataUkur>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(){
        launch {
            val db = buildDb(getApplication())
            dataLD_List.postValue(db.dataUkurDao().selectAllDataUkur())
        }
    }
}