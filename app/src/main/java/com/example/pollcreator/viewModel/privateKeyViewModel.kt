package com.example.pollcreator.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class privateKeyViewModel : ViewModel() {
    private var _privateKey = mutableStateOf("")
    private var _showDialog = mutableStateOf(true)

    val showDialog: State<Boolean> = _showDialog
    val privateKey: State<String?> = _privateKey

    fun setPrivateKey (value:String){
        _privateKey.value = value
    }
    fun setShowDialog(value: Boolean){
        _showDialog.value = value
    }

    init{
        setShowDialog(true)
    }

}