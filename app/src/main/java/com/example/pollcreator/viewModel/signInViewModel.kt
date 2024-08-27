package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.screens.MainActivity
import com.firebase.ui.auth.AuthUI.getApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class signInViewModel() : ViewModel() {
    val fireBaseDataModel: fireBaseDataModel = allSingeltonObjects.fireBaseDataModel

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
    private var _toastText = mutableStateOf<String?>(null)

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

    val toastText: State<String?> get() = _toastText

    // Getter and Setter functions

    // Getter functions are already provided through `val` properties

    fun setToastText(value: String?) {
        _toastText.value = value?: null
    }

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

    fun makeAllFieldsNull(){     // this was done to make the text field to null after each click   but this is hindering the profile button fx ,
                                    // to fix this , make a new system to fetch data from firebase to show the information of the user in the profile screen



        _aadharNo.value=""
        _password.value=""
        _age.value=""
        _gender.value=null
        _name.value=""
        _panNo.value=null
    }

    fun saveDataInSharedPreferences(context: Context){
        val sharedPreferences =context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        sharedPreferences.edit().putLong("aadharNo",_aadharNo.value.toLong()).apply()
        sharedPreferences.edit().putString("password",_password.value).apply()
        sharedPreferences.edit().putString("pan",_panNo.value).apply()
        sharedPreferences.edit().putBoolean("isadmin",_isAdmin.value).apply()


        makeAllFieldsNull()
         // adding all the information for login


    }





    suspend fun registerAdmin(){
        setIsSuccess( fireBaseDataModel.registerAdmin(
            user = UserOrAdmin(
                _name = _name.value,
                _aadharNo = _aadharNo.value.toLongOrNull()?: 0L,
                _password = _password.value,
                _gender = if (_gender.value.equals("male")) com.example.pollcreator.dataclass.Gender.MALE else com.example.pollcreator.dataclass.Gender.FEMALE,
                _age = _age.value.toIntOrNull()?: 0,
                adminOrNot = true,
                pan = _panNo.value

            )
        ))

        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()

        Log.d("from ViewModel", "${aadharNo.value}  ${password.value}  ")

    }

    suspend fun registerUser(){
        setIsSuccess( fireBaseDataModel.registerUser(
            user = UserOrAdmin(
                _name = _name.value,
                _aadharNo = _aadharNo.value.toLongOrNull()?: 0,
                _password = _password.value,
                _gender = if (_gender.value.equals("male")) com.example.pollcreator.dataclass.Gender.MALE else com.example.pollcreator.dataclass.Gender.FEMALE,
                _age = _age.value.toIntOrNull()?: 0,
                adminOrNot = false,
                pan = null
            )
        ))
        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()
        Log.d("from ViewModel", "${_aadharNo.value}  ${_password.value}  ")

    }

    suspend fun signInAdmin(){
        setIsSuccess( fireBaseDataModel.signInAdmin(
            aadharNo = _aadharNo.value.toLongOrNull()?: 0,
            password = _password.value,
            pan = _panNo.value?:null
        ))
        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()
        Log.d("from ViewModel", "${_aadharNo.value}  ${_password.value}  ")

    }

    suspend fun signInUser(){
        setIsSuccess( fireBaseDataModel.signInUser(
            aadharNo = _aadharNo.value.toLongOrNull()?: 0,
            password = _password.value
        ))
        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()
        Log.d("from ViewModel", "${_aadharNo.value}  ${_password.value}  ")

    }

    suspend fun checkCurrentSignIn() : Boolean{
        if(allSingeltonObjects.fireBaseDataModel.checkCurrentUser()==null){
            return false
        }else{
            return true
        }
    }

    suspend fun logout(){
        fireBaseDataModel.logout()
        makeAllFieldsNull()
    }

    suspend fun getUserDetails(){
        if(_aadharNo.value!=null){
            val userInfoFromFirebase = fireBaseDataModel.getUserDetails(_aadharNo.value.toLongOrNull()?: 100020003000 )
            setName(userInfoFromFirebase!!._name)
            setIsAdmin(userInfoFromFirebase!!.adminOrNot?: false)
            setAge(userInfoFromFirebase!!._age)
            setGender(userInfoFromFirebase!!._gender.toString())
            setPanNo(userInfoFromFirebase!!.pan)
            setNoOfPollCreated(userInfoFromFirebase!!.noOfPollCreated?: 0)
            setPassword(userInfoFromFirebase!!._password)

        }
    }


}