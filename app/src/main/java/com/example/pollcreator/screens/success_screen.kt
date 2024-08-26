package com.example.pollcreator.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pollcreator.R
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.viewModel.signInViewModel


@Preview
@Composable
public fun success_screen(
    modifier: Modifier = Modifier,
    btn1onClick: () -> Unit = {},
    navController: NavController = rememberNavController(),
) {

    val success_composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.success_animation))
    val fail_composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.fail_animation))

    val successanimationState =
        animateLottieCompositionAsState(composition = success_composition, iterations = 1)
    val failanimationState =
        animateLottieCompositionAsState(composition = fail_composition, iterations = 1)

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
                composition = if (allSingeltonObjects.signInViewModel.isSuccess.value) success_composition else fail_composition,
                progress = if (allSingeltonObjects.signInViewModel.isSuccess.value) successanimationState.progress else failanimationState.progress,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = if (allSingeltonObjects.signInViewModel.isSuccess.value) "Successful  !!!" else "Its on us ",  // apply back button if failed
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = TextOnBackgroundDark,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(50.dp))
            Card(modifier = Modifier
                .clickable {
                    if (allSingeltonObjects.signInViewModel.isSuccess.value) {
                        // success , now going to the dashboard
                        if (allSingeltonObjects.signInViewModel.isAdmin.value) {
                            navController.navigate("adminDashBoard"){
                                popUpTo("userOrAdmin") { inclusive = true }
                            }
                        }else{
                            navController.navigate("userDashBoard"){
                                popUpTo("userOrAdmin") { inclusive = true }
                            }
                        }
                    }

                    if(allSingeltonObjects.signInViewModel.isSuccess.value==false){
                        navController.navigate("userOrAdmin")
                    }
                }
                .size(height = 60.dp, width = 250.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    pressedElevation = 20.dp,
                    defaultElevation = 30.dp
                ),
                colors = CardDefaults.cardColors(containerColor = ButtonBackground)) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (allSingeltonObjects.signInViewModel.isSuccess.value) "Continue" else "Retry",  // apply back button if failed
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

            }


        }


    }

}