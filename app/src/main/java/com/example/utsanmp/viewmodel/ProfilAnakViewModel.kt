package com.example.utsanmp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.utsanmp.model.Profile
import com.example.utsanmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfilAnakViewModel(application: Application) : AndroidViewModel(application) ,CoroutineScope{
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    val profileLD = MutableLiveData<Profile?>()

    init {
        profileLD.value = Profile("", "", "")
    }

    fun refresh() {
        launch {
            val db = buildDb(getApplication())
            val profile = db.profileDao().getProfile()
            profileLD.postValue(profile ?: Profile("", "", ""))
        }
    }

    fun saveProfile() {
        val profile = profileLD.value
        if (profile == null) return

        launch{
            val db = buildDb(getApplication())
            db.profileDao().insert(profile)
            profileLD.postValue(profile)
        }

    }
}