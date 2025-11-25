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

    fun refresh() {
        launch {
            val db = buildDb(getApplication())
            val profile = db.profileDao().getProfile()
            profileLD.postValue(profile)
        }
    }

    fun saveProfile(_name: String, _dob: String, _gender: String) {
        val existingUuid = profileLD.value?.uuid ?: 0
        val newProfile = Profile(_name, _dob, _gender).apply { uuid = existingUuid }
        profileLD.value = newProfile
        launch{
            val db = buildDb(getApplication())
            db.profileDao().insert(newProfile)
        }
    }
}