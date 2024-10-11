package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Gender
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    private var _toastTextProfile = mutableStateOf<String?>(null)
    private var _isRegisteredInBC = mutableStateOf(true)

    // Public immutable state
    val isRegisteredInBC : State<Boolean> get() = _isRegisteredInBC
    fun setIsRegisteredInBC(value: Boolean){
        _isRegisteredInBC.value=value
    }
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
    val toastTextProfile: State<String?> get() = _toastTextProfile


    fun setToastText(value: String?) {
        _toastText.value = value?: null
    }
    fun setToastTextProfile(value: String?) {
        _toastTextProfile.value = value?: null
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

    fun setAadharNo(value: String) {
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

    fun setName(value: String?) {
        _name.value = value?: "anc"
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
        sharedPreferences.edit().putString("aadharNo",_aadharNo.value).apply()
        sharedPreferences.edit().putString("password",_password.value).apply()
        sharedPreferences.edit().putString("pan",_panNo.value).apply()
        sharedPreferences.edit().putBoolean("isadmin",_isAdmin.value).apply()


        makeAllFieldsNull()
         // adding all the information for login


    }

    fun checkCurrentUser():FirebaseUser?{
        return fireBaseDataModel.getUser()
    }





    suspend fun registerAdmin(context: Context){
        setIsSuccess( fireBaseDataModel.registerAdmin(
            user = UserOrAdmin(
                _name = _name.value,
                _aadharNo = _aadharNo.value,
                _password = _password.value,
                _gender = if (_gender.value.equals("male")) Gender.MALE else Gender.FEMALE,
                _age = _age.value.toIntOrNull()?: 0,
                adminOrNot = true,
                pan = _panNo.value

            )
        ))
        val sharedPreferences =context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("aadharNo",_aadharNo.value).apply()
        sharedPreferences.edit().putString("password",_password.value).apply()
        sharedPreferences.edit().putString("pan",_panNo.value).apply()
        sharedPreferences.edit().putBoolean("isadmin",true).apply()
        delay(300)
        getUserDetails()

        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()

        Log.d("from ViewModel", "${aadharNo.value}  ${password.value}  ")

    }

    suspend fun registerUser(context: Context){
        setIsSuccess( fireBaseDataModel.registerUser(
            user = UserOrAdmin(
                _name = _name.value,
                _aadharNo = _aadharNo.value,
                _password = _password.value,
                _gender = if (_gender.value.equals("male")) com.example.pollcreator.dataclass.Gender.MALE else com.example.pollcreator.dataclass.Gender.FEMALE,
                _age = _age.value.toIntOrNull()?: 0,
                adminOrNot = false,
                pan = null
            )
        ))
        val sharedPreferences =context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("aadharNo",_aadharNo.value).apply()
        sharedPreferences.edit().putString("password",_password.value).apply()
        sharedPreferences.edit().putString("pan",_panNo.value).apply()
        sharedPreferences.edit().putBoolean("isadmin",false).apply()
        delay(300)
        getUserDetails()

        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()
        Log.d("from ViewModel", "${_aadharNo.value}  ${_password.value}  ")

    }

    suspend fun signInAdmin(context: Context){

        val sharedPreferences =context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("aadharNo",_aadharNo.value).apply()
        sharedPreferences.edit().putString("password",_password.value).apply()
        sharedPreferences.edit().putString("pan",_panNo.value).apply()
        sharedPreferences.edit().putBoolean("isadmin",true).apply()
        setIsSuccess( fireBaseDataModel.signInAdmin(
            aadharNo = _aadharNo.value,
            password = _password.value,
            pan = _panNo.value?:null
        ))
        delay(300)
        getUserDetails()

        allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()
        Log.d("from ViewModel", "${_aadharNo.value}  ${_password.value}  ")

    }

    suspend fun signInUser(context: Context){
        setIsSuccess( fireBaseDataModel.signInUser(
            aadharNo = _aadharNo.value,
            password = _password.value
        ))

        val sharedPreferences =context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("aadharNo",_aadharNo.value).apply()
        sharedPreferences.edit().putString("password",_password.value).apply()
        sharedPreferences.edit().putBoolean("isadmin",false).apply()
        delay(300)

        getUserDetails()

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

    suspend fun logout(context: Context){

        fireBaseDataModel.logout()
        val sharedPreferences =context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        allSingeltonObjects.privateKeyViewModelObject= privateKeyViewModel()

        allSingeltonObjects.signInViewModel.setToastTextProfile("Logged Out Successfully !!!")

        makeAllFieldsNull()


    }

    suspend fun getUserDetails(){
        Log.d("aadhar check","${_aadharNo.value}")
        if(_aadharNo.value.length==12){

            CoroutineScope(Dispatchers.IO).launch {
                val userInfoFromFirebase = async { fireBaseDataModel.getUserDetails(_aadharNo.value)}.await()
                if(userInfoFromFirebase!=null){
                    setName(userInfoFromFirebase!!._name)
                    setIsAdmin(userInfoFromFirebase!!.adminOrNot ?: false)
                    setAge(userInfoFromFirebase!!._age)
                    setGender(userInfoFromFirebase!!._gender.toString())
                    setPanNo(userInfoFromFirebase!!.pan)
                    setNoOfPollCreated(userInfoFromFirebase!!.noOfPollCreated!!.toInt())
                    Log.d("SignInVM", "${noOfPollCreated.value}")
                    setPassword(userInfoFromFirebase!!._password)
                    setIsRegisteredInBC(userInfoFromFirebase._isRegisteredInBC)
                }

            }

        }
    }
    suspend fun getUserDetailsUser():UserOrAdmin{
        if(_aadharNo.value.length==12){
            getUserDetails()
            return UserOrAdmin(
                _aadharNo = aadharNo.value,
                _password = password.value,
                _gender=if(_gender.value=="male") Gender.MALE else Gender.FEMALE,
                _age = age.value.toInt(),
                _name = name.value,
                adminOrNot = isAdmin.value,
                pan = panNo.value,
                noOfPollCreated = noOfPollCreated.value,
                _isRegisteredInBC = isRegisteredInBC.value
            )

        }else{
            return UserOrAdmin(
                _aadharNo = "999999999999",
                _password = "999999999999",
                _gender=Gender.MALE ,
                _age = 18,
                _name = "dummy data",
                adminOrNot = true,
                pan = "9999999999",
                noOfPollCreated = 0,
                _isRegisteredInBC = true
            )
        }

    }


}