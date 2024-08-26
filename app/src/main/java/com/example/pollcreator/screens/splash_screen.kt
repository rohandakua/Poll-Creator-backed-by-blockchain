package com.example.pollcreator.screens


import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pollcreator.R
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.viewModel.signInViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.coroutineContext


@Composable
public fun splash_screen(
    modifier: Modifier = Modifier,
    navController: NavController= rememberNavController(),

) {
    val viewModel : signInViewModel = allSingeltonObjects.signInViewModel

    val success_composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.splash_screen_anim))

    val successanimationState = animateLottieCompositionAsState(composition = success_composition, iterations = 1, speed = 2.5f)


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            LottieAnimation(
                composition = success_composition,
                progress = successanimationState.progress ,
                modifier = Modifier.fillMaxSize(.9f)
            )


        }


    }
    LaunchedEffect(true) {
        delay(1500)
        if (viewModel.checkCurrentSignIn()){
            navController.popBackStack()
            if(viewModel.isAdmin.value){
                navController.navigate("adminDashBoard")
            }else{
                navController.navigate("userDashBoard")
            }
        }else{
            navController.popBackStack()
            navController.navigate("userOrAdmin")
        }

    }

}