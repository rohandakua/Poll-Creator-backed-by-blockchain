package com.example.pollcreator.onlineStorage


import android.util.Log
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.repository.signUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class fireBaseDataModel : signUpRepository {


    private lateinit var auth: FirebaseAuth

    init {
        getAuth()

    }

    fun getAuth(): Unit {
        auth = Firebase.auth
    }  // this should be called before set function


    override suspend fun registerUser(user: UserOrAdmin): Boolean {
        var emailOfUser = "thisis" + user._aadharNo.toString() + "@fakeaadhar.com"
        return try {
            auth.createUserWithEmailAndPassword(emailOfUser, user._password).await()
            allSingeltonObjects.referenceToUsers.child(user._aadharNo.toString()).setValue(user)
                .await()
            allSingeltonObjects.signInViewModel.setToastText("Registered Successfully !!!")
            true
        } catch (e: Exception) {
            Log.e("firebase", e.message.toString())
            allSingeltonObjects.signInViewModel.setToastText("${e.message.toString()}")
            false
        }
    }

    override suspend fun registerAdmin(user: UserOrAdmin): Boolean {
        var emailOfUser = "thisis" + user._aadharNo.toString() + "@fakeaadhar.com"
        return try {
            auth.createUserWithEmailAndPassword(emailOfUser, user._password).await()
            allSingeltonObjects.referenceToUsers.child(user._aadharNo.toString()).setValue(user)
                .await()
            allSingeltonObjects.signInViewModel.setToastText("Registered Successfully !!!")

            true
        } catch (e: Exception) {
            Log.e("firebase", e.message.toString())

            allSingeltonObjects.signInViewModel.setToastText("${e.message.toString()}")
            false
        }
    }

    override suspend fun signInAdmin(aadharNo: Long, password: String, pan: String?): Boolean {
        val emailOfUser = "thisis" + aadharNo.toString() + "@fakeaadhar.com"
        return try {
            auth.signInWithEmailAndPassword(emailOfUser, password).await()


            // checking if the pan no is valid or not
            val snapShot = allSingeltonObjects.referenceToUsers.child(aadharNo.toString()).get().await()
            val userInfoFromFirebase = snapShot.getValue(UserOrAdmin::class.java)
            if(userInfoFromFirebase!!.pan!=pan){
                allSingeltonObjects.signInViewModel.setToastText("Please enter right PAN number")
                return false
            }
            allSingeltonObjects.signInViewModel.setToastText("Signed In Successfully !!!")


            true
        } catch (e: Exception) {
            Log.e("firebase", e.message.toString())

            allSingeltonObjects.signInViewModel.setToastText("${e.message.toString()}")
            false
        }
    }

    override suspend fun signInUser(aadharNo: Long, password: String): Boolean {

        val emailOfUser = "thisis" + aadharNo.toString() + "@fakeaadhar.com"

        return try {
            auth.signInWithEmailAndPassword(emailOfUser, password).await()
            allSingeltonObjects.signInViewModel.setToastText("Signed In Successfully !!!")

            true
        } catch (e: Exception) {
            Log.e("firebase", e.message.toString())
            allSingeltonObjects.signInViewModel.setToastText("${e.message.toString()}")
            false
        }
    }

    override suspend fun checkCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override suspend fun logout() {
        auth.signOut()
        allSingeltonObjects.signInViewModel.setToastText("Logged Out Successfully !!!")

    }

    override suspend fun getUserDetails(aadharNo: Long): UserOrAdmin? {
        val snapShot = allSingeltonObjects.referenceToUsers.child(aadharNo.toString()).get().await()
        return snapShot.getValue(UserOrAdmin::class.java)

    }


}