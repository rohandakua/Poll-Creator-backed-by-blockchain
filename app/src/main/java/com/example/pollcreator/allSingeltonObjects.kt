package com.example.pollcreator

import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.onlineStorage.web3jDataModel
import com.example.pollcreator.rubbish.helperFunctions
import com.example.pollcreator.viewModel.privateKeyViewModel
import com.example.pollcreator.viewModel.profileViewModel
import com.example.pollcreator.viewModel.signInViewModel
import com.google.firebase.database.FirebaseDatabase


object allSingeltonObjects {


    val fireBaseDataModel: fireBaseDataModel = fireBaseDataModel()

    val signInViewModel = signInViewModel()

    val profileViewModel : profileViewModel = profileViewModel()

    val referenceToUsers = FirebaseDatabase.getInstance().reference.child("users")       // this is the reference to the users table

    val referenceToPolls = FirebaseDatabase.getInstance().reference.child("polls")       // this is the refernce to the polls table

    val referenceToAllVoters = FirebaseDatabase.getInstance().reference.child("allvoters")

    val referenceToAllAdmins = FirebaseDatabase.getInstance().reference.child("alladmins")

    val helperFunctions = helperFunctions()

    val apiKey = "zWgAYQQLQFteREbHKp7F5RakZ-a_8gXA"

    val sepoliaUrl = "https://eth-sepolia.g.alchemy.com/v2/${apiKey}"

    val contractAddress = "0xe63df052e96c7AB378769121288664A1A6F94EEC"

    val privateKeyViewModelObject = privateKeyViewModel()


}