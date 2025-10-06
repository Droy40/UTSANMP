package com.example.utsanmp.viewmodel

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ProfilAnakViewModel(application: Application) : AndroidViewModel(application) {
    private val PREFS_NAME = "profile_preference"
    private val KEY_NAME = "key_name"
    private val KEY_DOB = "key_dob"
    private val KEY_GENDER = "key_gender"

    private val prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val nameLD = MutableLiveData<String>()
    val dobLD = MutableLiveData<String>()
    val genderLD = MutableLiveData<String>()

    init {
        loadFromPreference()
    }
    private fun loadFromPreference() {
        nameLD.value = prefs.getString(KEY_NAME, "")
        dobLD.value = prefs.getString(KEY_DOB, "")
        genderLD.value = prefs.getString(KEY_GENDER, "")
    }
    fun saveProfile(_name: String, _dob: String, _gender: String) {
        prefs.edit(commit = false) {
            putString(KEY_NAME, _name)
            putString(KEY_DOB, _dob)
            putString(KEY_GENDER, _gender)
        }
        nameLD.value = _name
        dobLD.value = _dob
        genderLD.value = _gender
    }
}