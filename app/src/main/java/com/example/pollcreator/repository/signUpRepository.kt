package com.example.pollcreator.repository

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.pollcreator.dataclass.Gender
import com.example.pollcreator.dataclass.UserOrAdmin
import com.example.pollcreator.viewModel.signInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface signUpRepository{
    suspend fun registerUser( user : UserOrAdmin) : Boolean   // take the input of a user and make changes in the database

    suspend fun registerAdmin(user: UserOrAdmin):Boolean // take the input of a Admin and make the changes in the database
    suspend fun signInAdmin(aadharNo: Long, password: String, pan: String?): Boolean   // take the input as aadharNo and pan and password and verify
    suspend fun signInUser(aadharNo: Long, password: String): Boolean   // take the input as aadharNo and password and verify)
    //  suspend fun changePassword(aadharNo: Long , oldPassword : String , newPassword : String) : Boolean
         // implement this function later
    suspend fun checkCurrentUser() : FirebaseUser?
    suspend fun logout()

    suspend fun getUserDetails(aadharNo: Long) : UserOrAdmin?


}