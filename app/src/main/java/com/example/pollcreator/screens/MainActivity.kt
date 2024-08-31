package com.example.pollcreator.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.navigation.navController
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.PollCreatorBackedByBlockchainTheme
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()
        setContent {
            PollCreatorBackedByBlockchainTheme {
                val sharedPreferences = this.getSharedPreferences("SignIn", MODE_PRIVATE)


                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(MainBackground)){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .systemBarsPadding()
                            .background(MainBackground)// Add padding for status bar
                    ) {
                        navController(viewModel = allSingeltonObjects.signInViewModel)


                    }
                }


            }
        }
    }

}
