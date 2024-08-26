package com.example.pollcreator.screens

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.R
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.ButtonBackground
import kotlinx.coroutines.flow.callbackFlow

@Preview
@Composable
fun login_register(
    modifier: Modifier = Modifier,
    btn1text: String = "Login",
    btn2text: String = "Register",
    navController: NavController = rememberNavController(),
    isAdmin : Boolean=false
) {
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


            Image(
                painter = painterResource(id = R.drawable.raise_hand),
                contentDescription = "ballot icon",
                modifier = Modifier.size(height = 300.dp, width = 300.dp),
                contentScale = ContentScale.Fit,

                )
            Spacer(modifier = Modifier.size(100.dp))
            Card(modifier = Modifier
                .clickable { navController.navigate("loginOrSignUp/${isAdmin}/${true}") }
                .size(height = 60.dp, width = 250.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(pressedElevation = 20.dp, defaultElevation = 30.dp),
                colors = CardDefaults.cardColors(containerColor = ButtonBackground)) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = btn1text,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

            }
            Spacer(modifier = Modifier.size(30.dp))

            Card(modifier = Modifier
                .clickable { navController.navigate("loginOrSignUp/${isAdmin}/${false}") }
                .size(height = 60.dp, width = 250.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(pressedElevation = 20.dp, defaultElevation = 30.dp),
                colors = CardDefaults.cardColors(ButtonBackground)) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = btn2text,
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