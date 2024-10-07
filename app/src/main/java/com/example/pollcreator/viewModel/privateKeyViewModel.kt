package com.example.pollcreator.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class privateKeyViewModel : ViewModel() {
    private var _privateKey = mutableStateOf("")
    private var _showDialog = mutableStateOf(true)
    private var _showHelp = mutableStateOf(false)

    val showHelp: State<Boolean> = _showHelp
    val showDialog: State<Boolean> = _showDialog
    val privateKey: State<String?> = _privateKey

    fun setShowHelp(value: Boolean){
        _showHelp.value = value
    }
    fun setPrivateKey (value:String){
        _privateKey.value = value
        Log.d("privateKeyViewModel", "setPrivateKey: ${_privateKey.value}")
    }
    fun setShowDialog(value: Boolean){
        _showDialog.value = value
    }

    init{
        setShowDialog(true)
    }

}