package com.example.pollcreator

import android.content.Context
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.rubbish.helperFunctions
import com.example.pollcreator.screens.MainActivity
import com.example.pollcreator.viewModel.profileViewModel
import com.example.pollcreator.viewModel.signInViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


object allSingeltonObjects {


    val fireBaseDataModel: fireBaseDataModel = fireBaseDataModel()

    val signInViewModel = signInViewModel()

    val profileViewModel : profileViewModel = profileViewModel()

    val referenceToUsers = FirebaseDatabase.getInstance().reference.child("users")       // this is the reference to the users table

    val referenceToPolls = FirebaseDatabase.getInstance().reference.child("polls")       // this is the refernce to the polls table

    val helperFunctions = helperFunctions()
}