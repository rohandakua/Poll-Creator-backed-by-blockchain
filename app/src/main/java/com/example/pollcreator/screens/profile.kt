package com.example.pollcreator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBackgroundLight
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import com.example.pollcreator.viewModel.signInViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.core.Context
import kotlinx.coroutines.delay




@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
public fun profile(
    modifier: Modifier = Modifier,
    onBtn1Click: () -> Unit = {},
    onBtn2Click: () -> Unit = {},
    textOnBtn2: String = "Quit being Admin",

    navController: NavController = rememberNavController()

) {

    LaunchedEffect(allSingeltonObjects.profileViewModel.isLoading.value) {
        allSingeltonObjects.profileViewModel.getUserDetails()
    }
    val s  = remember {
        mutableStateOf("Loading .....")
    }



    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        if (allSingeltonObjects.profileViewModel.isLoading.value) {
            Column (Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.Center){

                Text(text = s.value)

            }

        } else {
            Column {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(14.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Profile Details",
                        fontSize = 52.sp,
                        color = TextOnBackgroundDark,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBackgroundLight),
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .fillMaxHeight(.5f),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {


                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                modifier = Modifier
                                    .fillMaxWidth(.95f)
                                    .weight(1f)
                                    .padding(top = 8.dp, bottom = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = allSingeltonObjects.profileViewModel.name.value.capitalize(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                            .padding(
                                                top = 4.dp, start = 6.dp, end = 6.dp, bottom = 4.dp
                                            ),
                                        textAlign = TextAlign.Center,
                                        fontSize = 26.sp,
                                        color = TextOnBackgroundDark
                                    )

                                }

                            }


                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                modifier = Modifier
                                    .fillMaxWidth(.95f)
                                    .weight(1f)
                                    .padding(top = 8.dp, bottom = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = allSingeltonObjects.profileViewModel.age.value.toString(),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                            .padding(
                                                top = 4.dp, start = 6.dp, end = 6.dp, bottom = 4.dp
                                            ),
                                        textAlign = TextAlign.Center,
                                        fontSize = 26.sp,
                                        color = TextOnBackgroundDark
                                    )

                                }

                            }
                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                modifier = Modifier
                                    .fillMaxWidth(.95f)
                                    .weight(1f)
                                    .padding(top = 8.dp, bottom = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = allSingeltonObjects.profileViewModel.gender.value
                                            ?: "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                            .padding(
                                                top = 4.dp, start = 6.dp, end = 6.dp, bottom = 4.dp
                                            ),
                                        textAlign = TextAlign.Center,
                                        fontSize = 26.sp,
                                        color = TextOnBackgroundDark
                                    )

                                }

                            }

                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                modifier = Modifier
                                    .fillMaxWidth(.95f)
                                    .weight(1f)
                                    .padding(top = 8.dp, bottom = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = allSingeltonObjects.profileViewModel.aadharNo.value,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)
                                            .padding(
                                                top = 4.dp, start = 6.dp, end = 6.dp, bottom = 4.dp
                                            ),
                                        textAlign = TextAlign.Center,
                                        fontSize = 26.sp,
                                        color = TextOnBackgroundDark
                                    )

                                }

                            }



                            if (allSingeltonObjects.profileViewModel.panNo.value != null && allSingeltonObjects.profileViewModel.panNo.value != "") {
                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                    modifier = Modifier
                                        .fillMaxWidth(.95f)
                                        .weight(1f)
                                        .padding(top = 8.dp, bottom = 8.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = allSingeltonObjects.profileViewModel.panNo.value
                                                ?: "",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally)
                                                .padding(
                                                    top = 4.dp,
                                                    start = 6.dp,
                                                    end = 6.dp,
                                                    bottom = 4.dp
                                                ),
                                            textAlign = TextAlign.Center,
                                            fontSize = 26.sp,
                                            color = TextOnBackgroundDark
                                        )

                                    }

                                }
                            }


                        }

                        //

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        if (allSingeltonObjects.profileViewModel.isAdmin.value) {
                            Card(modifier = Modifier
                                .clickable { onBtn2Click }
                                .size(height = 55.dp, width = 300.dp),
                                shape = RoundedCornerShape(20.dp),
                                elevation = CardDefaults.cardElevation(
                                    pressedElevation = 20.dp, defaultElevation = 30.dp
                                ),
                                colors = CardDefaults.cardColors(ButtonBackground)) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = textOnBtn2,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        letterSpacing = 2.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                        }
                        Card(modifier = Modifier
                            .clickable { onBtn1Click }
                            .size(height = 55.dp, width = 300.dp),
                            shape = RoundedCornerShape(20.dp),
                            elevation = CardDefaults.cardElevation(
                                pressedElevation = 20.dp, defaultElevation = 30.dp
                            ),
                            colors = CardDefaults.cardColors(ButtonBackground)) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Change Password",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                        Card(modifier = Modifier
                            .clickable {
                                allSingeltonObjects.signInViewModel.makeAllFieldsNull()
                                allSingeltonObjects.profileViewModel.makeAllFieldsNull()
                                navController.navigate("UserOrAdmin") }
                            .size(height = 55.dp, width = 300.dp),
                            shape = RoundedCornerShape(20.dp),
                            elevation = CardDefaults.cardElevation(
                                pressedElevation = 20.dp, defaultElevation = 30.dp
                            ),
                            colors = CardDefaults.cardColors(ButtonBackground)) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Logout",
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
        }


    }

}