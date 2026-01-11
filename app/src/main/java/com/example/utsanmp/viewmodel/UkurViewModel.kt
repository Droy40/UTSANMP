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

    fun save() {
        val data = dataUkurLD.value ?: return
        launch {
            val db = buildDb(getApplication())
            db.dataUkurDao().insertAll(data)
        }
    }

    fun clear(){
        dataUkurLD.value = DataUkur(null,null,null)
    }
}