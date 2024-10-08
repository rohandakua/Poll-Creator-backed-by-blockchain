package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.dataclass.allAdminObj
import com.firebase.ui.auth.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class profileViewModel : ViewModel() {


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

    val toastText: State<String?> get() = _toastText


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

    fun makeAllFieldsNull(){
        _aadharNo.value=""
        _password.value=""
        _age.value=""
        _gender.value=null
        _name.value=""
        _panNo.value=null
    }

    fun getUserDataFromSharedPrefernce(context: Context){
        val sharedPreferences = context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        val aadharNoget = sharedPreferences.getLong("aadharNo",0L)
        val panNoget = sharedPreferences.getString("pan","")
        val passwordget = sharedPreferences.getString("password","")
        val isAdminget = sharedPreferences.getBoolean("isadmin",false)
        if(isAdminget){
            CoroutineScope(Dispatchers.IO).launch {
                allSingeltonObjects.signInViewModel.setAadharNo(aadharNoget)
                allSingeltonObjects.signInViewModel.setPassword(passwordget?: "")
                allSingeltonObjects.signInViewModel.setPanNo(panNoget)
                allSingeltonObjects.profileViewModel.setIsAdmin(true)
                allSingeltonObjects.signInViewModel.signInAdmin()
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
            allSingeltonObjects.signInViewModel.setAadharNo(aadharNoget)
            allSingeltonObjects.signInViewModel.setPassword(passwordget?: "")
                allSingeltonObjects.profileViewModel.setIsAdmin(false)
            allSingeltonObjects.signInViewModel.signInUser()
                }

        }

    }

    suspend fun getUserDetails(context: Context){


        viewModelScope.launch {
                val sharedPreferences=context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
                getUserDataFromSharedPrefernce(context)
                Log.d("reached sharefPref section in get data","")
                _isLoading.value = true
                setAadharNo(sharedPreferences.getLong("aadharNo",0L)!!)
                setPassword(sharedPreferences.getString("password","")!!)
                setIsAdmin(sharedPreferences.getBoolean("isadmin",false)!!)
                setPanNo(sharedPreferences.getString("pan",""))
                if(_isAdmin.value){
                    allSingeltonObjects.signInViewModel.setAadharNo(_aadharNo.value.toLong())
                    allSingeltonObjects.signInViewModel.setPassword(_password.value)
                    allSingeltonObjects.signInViewModel.setPanNo(_panNo.value)
                    allSingeltonObjects.signInViewModel.signInAdmin()
                }else{
                    allSingeltonObjects.signInViewModel.setAadharNo(_aadharNo.value.toLong())
                    allSingeltonObjects.signInViewModel.setPassword(_password.value)
                    allSingeltonObjects.signInViewModel.signInUser()
                }
                allSingeltonObjects.signInViewModel.getUserDetails()
                getCopyOfDetailsFromSignIn()
                _isLoading.value=false
                Log.d("reached sharefPref section in get data","")
        }
    }
    fun getCopyOfDetailsFromSignIn(){
        _aadharNo.value = allSingeltonObjects.signInViewModel.aadharNo.value
        _password.value = allSingeltonObjects.signInViewModel.password.value
        _panNo.value = allSingeltonObjects.signInViewModel.panNo.value
        _isAdmin.value = allSingeltonObjects.signInViewModel.isAdmin.value
        _age.value = allSingeltonObjects.signInViewModel.age.value
        _gender.value = allSingeltonObjects.signInViewModel.gender.value
        _name.value = allSingeltonObjects.signInViewModel.name.value
        _noOfPollCreated.value = allSingeltonObjects.signInViewModel.noOfPollCreated.value

    }
    suspend fun changePassword(newpassword: String){

        Log.d("from change password","${allSingeltonObjects.signInViewModel.aadharNo.value}")
        val user = allSingeltonObjects.signInViewModel.checkCurrentUser()
        user?.let {
            it.updatePassword(newpassword).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    setIsSuccess(true)
                    setToastText("Password Changed Successfully !!!")
                    // change in the realtime db
                    allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("_password").setValue(newpassword)

                }else{
                    setIsSuccess(false)
                    setToastText("Password Not Changed !!!")

                }

            }


        }
    }


    suspend fun changeToAdmin(pan : String,context: Context){
        // changing the adminOrNot to true and also adding pan no then reloading the admin dashboard screen
        setPanNo(pan)
        setIsAdmin(true)
        allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("pan").setValue(pan)
        allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("adminOrNot").setValue(true)
        val savedPreferences = context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        savedPreferences.edit().putBoolean("isadmin",true).apply()
        allSingeltonObjects.signInViewModel.setIsAdmin(true)
        allSingeltonObjects.signInViewModel.setPanNo(pan)
        allSingeltonObjects.signInViewModel.signInAdmin()
        val user = allAdminObj(
            _aadharNo = allSingeltonObjects.signInViewModel.aadharNo.value.toLong(),
            _isRegisteredAsAdmin = true,
            _pollsCreated = mutableListOf(),
            _pollsParticipated = mutableListOf()
        )
        // putting the user in alladmin
        allSingeltonObjects.referenceToAllAdmins.child(allSingeltonObjects.signInViewModel.aadharNo.value).setValue(user).await()
        allSingeltonObjects.referenceToAllVoters.child(allSingeltonObjects.signInViewModel.aadharNo.value).child("_isAdmin").setValue(true).await()


    }

    suspend fun changeToUser(context: Context){
        setPanNo(null)
        setIsAdmin(false)
        allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("pan").setValue(null)
        allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("adminOrNot").setValue(false)
        val savedPreferences = context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        savedPreferences.edit().putBoolean("isadmin",false).apply()
        allSingeltonObjects.signInViewModel.setIsAdmin(false)
        allSingeltonObjects.signInViewModel.setPanNo(null)
        allSingeltonObjects.signInViewModel.signInUser()
        //deleting the data from alladmin
        allSingeltonObjects.referenceToAllAdmins.child(allSingeltonObjects.signInViewModel.aadharNo.value).removeValue().await()

        allSingeltonObjects.referenceToAllVoters.child(allSingeltonObjects.signInViewModel.aadharNo.value).child("_isAdmin").setValue(false).await()

    }

    suspend fun getPreviousPolls(){       // get previous polls till now
        TODO()
    }
    suspend fun getPollsCreated(){        // get all active the polls that are created by the admin
        TODO()
    }
    suspend fun getPrevPollsCreated(){    // get all the polls that were created by the admin
        TODO()
    }
    suspend fun getUpcomingPolls(){       // get all the upcoming polls
        TODO()
    }






}