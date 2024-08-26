package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.screens.MainActivity
import kotlinx.coroutines.launch

class profileViewModel : ViewModel() {

//    val sharedPreferences = MainActivity().getSharedPreferences("user_ref",Context.MODE_PRIVATE)
    // Private mutable state
    private var _isLogin = mutableStateOf(true)
    private var _isAdmin = mutableStateOf(true)
    private var _aadharNo = mutableStateOf("")
    private var _password = mutableStateOf("")
    private var _age = mutableStateOf("")
    private var _gender = mutableStateOf<String?>(null)
    private var _name = mutableStateOf("")
    private var _panNo = mutableStateOf<String?>(null)
    private var _isSuccess = mutableStateOf(true)
    private var _noOfPollCreated = mutableStateOf(0)

    private var _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading


    // Public immutable state
    val isLogin: State<Boolean> get() = _isLogin
    val isAdmin: State<Boolean> get() = _isAdmin
    val aadharNo: State<String> get() = _aadharNo
    val password: State<String> get() = _password
    val age: MutableState<String> get() = _age
    val gender: State<String?> get() = _gender
    val name: State<String> get() = _name
    val panNo: State<String?> get() = _panNo
    val isSuccess: State<Boolean> get() = _isSuccess
    val noOfPollCreated: State<Int> get() = _noOfPollCreated

    // Getter and Setter functions

    // Getter functions are already provided through `val` properties

    fun setIsLogin(value: Boolean) {
        _isLogin.value = value
    }

    fun setIsSuccess(value: Boolean) {
        _isSuccess.value = value
    }

    fun setIsAdmin(value: Boolean) {
        _isAdmin.value = value
    }

    fun setAadharNo(value: Long) {
        _aadharNo.value = value.toString()
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun setAge(value: Int) {
        _age.value = value.toString()
    }

    fun setGender(value: String?) {
        _gender.value = value
    }

    fun setName(value: String) {
        _name.value = value
    }

    fun setPanNo(value: String?) {
        _panNo.value = value
    }
    fun setNoOfPollCreated(value: Int) {
        _noOfPollCreated.value = value
    }

    fun makeAllFieldsNull(){
        _aadharNo.value=""
        _password.value=""
        _age.value=""
        _gender.value=null
        _name.value=""
        _panNo.value=null
    }

    suspend fun getUserDetails(){
        viewModelScope.launch {

            if(_aadharNo.value!=null){
                _isLoading.value = true
                val userInfoFromFirebase = allSingeltonObjects.fireBaseDataModel.getUserDetails(_aadharNo.value.toLong() )
                setName(userInfoFromFirebase!!._name)
                setIsAdmin(userInfoFromFirebase!!.adminOrNot?: false)
                setAge(userInfoFromFirebase!!._age)
                setGender(userInfoFromFirebase!!._gender.toString())
                setPanNo(userInfoFromFirebase!!.pan)
                setNoOfPollCreated(userInfoFromFirebase!!.noOfPollCreated?: 0)
                setPassword(userInfoFromFirebase!!._password)
                setAadharNo(userInfoFromFirebase!!._aadharNo)
                Log.d("from getUserDetails" , "reached is loading = false")
                _isLoading.value=false
                Log.d("from getUserDetails" , "passed is loading = false")

            }
//            else{
//                _isLoading.value = true
//                setAadharNo(sharedPreferences.getString("aadharNo","")!!.toLong())
//                setPassword(sharedPreferences.getString("password","")!!)
//                setIsAdmin(sharedPreferences.getString("isAdmin","")!!.toBoolean())
//                setPanNo(sharedPreferences.getString("panNo",""))
//
//                if(_isAdmin.value){
//                    allSingeltonObjects.signInViewModel.setAadharNo(_aadharNo.value.toLong())
//                    allSingeltonObjects.signInViewModel.setPassword(_password.value)
//                    allSingeltonObjects.signInViewModel.setPanNo(_panNo.value)
//                    allSingeltonObjects.signInViewModel.signInAdmin()
//                    getCopyOfDetailsFromSignIn()
//                }else{
//                    allSingeltonObjects.signInViewModel.setAadharNo(_aadharNo.value.toLong())
//                    allSingeltonObjects.signInViewModel.setPassword(_password.value)
//                    allSingeltonObjects.signInViewModel.signInUser()
//                    getCopyOfDetailsFromSignIn()
//                }
//
//
//                _isLoading.value = false
//
//            }
        }
    }
    fun getCopyOfDetailsFromSignIn(){
        _aadharNo.value = allSingeltonObjects.signInViewModel.aadharNo.value
        _password.value = allSingeltonObjects.signInViewModel.password.value
        _panNo.value = allSingeltonObjects.signInViewModel.panNo.value
    }

    suspend fun logout(){
        allSingeltonObjects.fireBaseDataModel.logout()
        makeAllFieldsNull()

    }
}