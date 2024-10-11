package com.example.pollcreator.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.dataclass.allAdminObj
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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
    private var _pollItem = mutableStateOf<Poll?>(null)

    private var _listpm = MutableLiveData<MutableList<Poll>>(mutableListOf())

    val listpm : LiveData<MutableList<Poll>> = _listpm
    fun addItemTolist(value:Poll){
        _listpm.value=_listpm.value?.apply { add(value) }
    }

    fun clearList(){
        _listpm.value= mutableListOf()
    }

    val isLoading: State<Boolean> get() = _isLoading
    private var _isRegisteredInBC = mutableStateOf(true)

    // Public immutable state
    val isRegisteredInBC : State<Boolean> get() = _isRegisteredInBC
    fun setIsRegisteredInBC(value: Boolean){
        _isRegisteredInBC.value=value
    }


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
    val pollItem :State<Poll?> get() = _pollItem

    fun setPollItem(value:Poll?){
        _pollItem.value=value?: Poll(
            _pollId = "1000100010001",
            _pollCreatedBy = "100010001000",
            _agendaOfPoll = "pollAgenda",
            _eligibleVoterAge = 20,
            _startTime = 1633036800000,
            _endTime = 1633036899999
        )
    }

    fun getPollItem(): Poll? {
        return pollItem.value
    }


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
        val aadharNoget = sharedPreferences.getString("aadharNo","100010001000")
        val panNoget = sharedPreferences.getString("pan","")
        val passwordget = sharedPreferences.getString("password","")
        val isAdminget = sharedPreferences.getBoolean("isadmin",false)
        if(isAdminget){
            CoroutineScope(Dispatchers.IO).launch {
                allSingeltonObjects.signInViewModel.setAadharNo(aadharNoget?: "100010001000")
                allSingeltonObjects.signInViewModel.setPassword(passwordget?: "")
                allSingeltonObjects.signInViewModel.setPanNo(panNoget)
                allSingeltonObjects.profileViewModel.setIsAdmin(true)
                allSingeltonObjects.signInViewModel.signInAdmin(context)
            }
        }else{
            CoroutineScope(Dispatchers.IO).launch {
            allSingeltonObjects.signInViewModel.setAadharNo(aadharNoget?: "100010001000")
            allSingeltonObjects.signInViewModel.setPassword(passwordget?: "")
                allSingeltonObjects.profileViewModel.setIsAdmin(false)
            allSingeltonObjects.signInViewModel.signInUser(context)
                }

        }

    }

    suspend fun getUserDetails(context: Context){


        viewModelScope.launch {
                val sharedPreferences=context.getSharedPreferences("LoginData", Context.MODE_PRIVATE)
                getUserDataFromSharedPrefernce(context)
                Log.d("reached sharefPref section in get data","")
                _isLoading.value = true
                setAadharNo(sharedPreferences.getString("aadharNo","100010001000")!!)
                Log.d("aadhar no from shared pref","$aadharNo")
                setPassword(sharedPreferences.getString("password","")!!)
                setIsAdmin(sharedPreferences.getBoolean("isadmin",false)!!)
                setPanNo(sharedPreferences.getString("pan",""))
                if(_isAdmin.value){
                    allSingeltonObjects.signInViewModel.setAadharNo(_aadharNo.value)
                    allSingeltonObjects.signInViewModel.setPassword(_password.value)
                    allSingeltonObjects.signInViewModel.setPanNo(_panNo.value)
                    allSingeltonObjects.signInViewModel.signInAdmin(context)
                }else{
                    allSingeltonObjects.signInViewModel.setAadharNo(_aadharNo.value)
                    allSingeltonObjects.signInViewModel.setPassword(_password.value)
                    allSingeltonObjects.signInViewModel.signInUser(context)
                }
                allSingeltonObjects.signInViewModel.getUserDetails()
                getCopyOfDetailsFromSignIn()
                _isLoading.value=false
                Log.d("reached sharefPref section in get data","")
        }
    }
    fun getCopyOfDetailsFromSignIn(){
        CoroutineScope(Dispatchers.IO).launch {


            _aadharNo.value = allSingeltonObjects.signInViewModel.aadharNo.value
            setAadharNo(allSingeltonObjects.signInViewModel.aadharNo.value)
            _password.value = allSingeltonObjects.signInViewModel.password.value
            setPassword(allSingeltonObjects.signInViewModel.password.value)
            _panNo.value = allSingeltonObjects.signInViewModel.panNo.value
            setPanNo(allSingeltonObjects.signInViewModel.panNo.value)
            _isAdmin.value = allSingeltonObjects.signInViewModel.isAdmin.value
            setIsAdmin(allSingeltonObjects.signInViewModel.isAdmin.value)
            _age.value = allSingeltonObjects.signInViewModel.age.value
            setAge(allSingeltonObjects.signInViewModel.age.value.toIntOrNull()?: 20)
            Log.d("age","${age.value.toString()}")
            _gender.value = allSingeltonObjects.signInViewModel.gender.value
            setGender(allSingeltonObjects.signInViewModel.gender.value)
            _name.value = allSingeltonObjects.signInViewModel.name.value
            setName(allSingeltonObjects.signInViewModel.name.value)
            _noOfPollCreated.value = allSingeltonObjects.signInViewModel.noOfPollCreated.value
            setNoOfPollCreated(allSingeltonObjects.signInViewModel.noOfPollCreated.value)
            _isRegisteredInBC.value = allSingeltonObjects.signInViewModel.isSuccess.value
            setIsRegisteredInBC(allSingeltonObjects.signInViewModel.isSuccess.value)
        }

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
        allSingeltonObjects.signInViewModel.signInAdmin(context)
        val user = allAdminObj(
            _aadharNo = allSingeltonObjects.signInViewModel.aadharNo.value,
            _isRegisteredAsAdmin = true,
            _pollsCreated = mutableListOf(),
            _pollsParticipated = mutableListOf()
        )
        // putting the user in alladmin
        allSingeltonObjects.referenceToAllAdmins.child(allSingeltonObjects.signInViewModel.aadharNo.value).setValue(user).await()
        try {
            var userOrAdmin = UserOrAdmin()
            CoroutineScope(Dispatchers.IO).launch {
                if(allSingeltonObjects.signInViewModel.aadharNo.value.length==12){
                    userOrAdmin = allSingeltonObjects.signInViewModel.getUserDetailsUser()
                }
            }
            allSingeltonObjects.web3jDataModel.becomeAdmin(
                userOrAdmin = userOrAdmin
            )

        }catch (e :Exception){
            Log.d("profileViewModel","web3j $e")
        }
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
        allSingeltonObjects.signInViewModel.signInUser(context)
        //deleting the data from alladmin
        allSingeltonObjects.referenceToAllAdmins.child(allSingeltonObjects.signInViewModel.aadharNo.value).removeValue().await()
        try {
            allSingeltonObjects.web3jDataModel.deleteAdmin(
                userOrAdmin = UserOrAdmin(
                    _aadharNo = aadharNo.value
                )
            )
        }catch (e :Exception){
            Log.d("profileViewModel","web3j $e")
        }
        allSingeltonObjects.referenceToAllVoters.child(allSingeltonObjects.signInViewModel.aadharNo.value).child("_isAdmin").setValue(false).await()

    }

    suspend fun getPreviousPolls(): MutableList<Poll> {       // get previous polls till now that user have participated
        var list = mutableListOf<Poll>()
        try {
           if(allSingeltonObjects.helperFunctions.isValidPrivateKey(allSingeltonObjects.privateKeyViewModelObject.privateKey.value)){
               CoroutineScope(Dispatchers.IO).launch {
                   list = async {
                       allSingeltonObjects.web3jDataModel.getPreviousPoll(aadharNo.value)
                           .toMutableList()
                   }.await()

                   for( poll in list){
                       addItemTolist(poll)
                   }

                   withContext(Dispatchers.Main){
                       for( poll in list){
                           addItemTolist(poll)
                       }

                   }

                   Log.d("getPreviousPolls", "called ${list.size}")
               }
           }
        }catch (e : Exception){
            Log.d("profileViewModel","from getPrevPoll $e")
        }
        return list
    }
    suspend fun getPollsCreated(): MutableList<Poll> {        // get all active the polls that are created by the admin
        var list = mutableListOf<Poll>()
        clearList()
        try {CoroutineScope(Dispatchers.IO).launch {
            list = async {
                allSingeltonObjects.web3jDataModel.getPrevPollCreatedByAdmin(aadharNo.value)
                    .toMutableList()
            }.await()
            withContext(Dispatchers.Main){
                clearList()
                for( poll in list){
                    addItemTolist(poll)
                }

            }

            Log.d("getPollsCreated", "called ${list.size}")
        }
        }catch (e : Exception){
            Log.d("profileViewModel","from getPollCreated $e")
        }
        return list
    }
    suspend fun getPrevPollsCreated(): MutableList<Poll> {    // get all the polls that were created by the admin
        var list = mutableListOf<Poll>()
        try {CoroutineScope(Dispatchers.IO).launch {
            list = async {
                allSingeltonObjects.web3jDataModel.getPrevPollCreatedByAdmin(aadharNo.value)
                    .toMutableList()
            }.await()
            delay(1000)

            withContext(Dispatchers.Main){
                clearList()
                for( poll in list){
                    addItemTolist(poll)
                }

            }

            Log.d("getPrevPollsCreated", "called ${list.size}")
        }
        }catch (e : Exception){
            Log.d("profileViewModel","from getPrevPollCreated $e")
        }


        return list
    }
    suspend fun getUpcomingPolls(): MutableList<Poll> {       // get all the upcoming polls
        var list = mutableListOf<Poll>()
        try {CoroutineScope(Dispatchers.IO).launch {
            if (allSingeltonObjects.checkWeb3j()) {
                list = async {
                    allSingeltonObjects.web3jDataModel.getAllUpcomingPoll(age.value.toInt())
                        .toMutableList()
                }.await()
                withContext(Dispatchers.Main){
                    clearList()
                    for( poll in list){
                        addItemTolist(poll)
                    }

                }

            }


            Log.d("getUpcomingPolls", "called ${list.size}")
        }
        }catch (e : Exception){
            Log.d("profileViewModel","$e")
        }
        return list
    }

    suspend fun getUpcomingPollsForAdminToParticipate(): MutableList<Poll> {    // get all the polls where the admin can participate
        var list = mutableListOf<Poll>()
        try {CoroutineScope(Dispatchers.IO).launch {
            list = async {
                allSingeltonObjects.web3jDataModel.getPollThatAdminCanPartiticipate(aadharNo.value)
                    .toMutableList()
            }.await()
            withContext(Dispatchers.Main){
                clearList()
                for( poll in list){
                    addItemTolist(poll)
                }

            }

            Log.d("getPollToParticipate", "called ${list.size}")
        }
        }catch (e : Exception){
            Log.d("profileViewModel","from getPollToParticipate $e")
        }

        return list
    }






}