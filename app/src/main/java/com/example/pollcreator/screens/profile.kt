package com.example.pollcreator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pollcreator.R
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBackgroundLight
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
public fun profile(
    modifier: Modifier = Modifier,
    isAdmin: Boolean = true,
    onPrevBtnClick: () -> Unit = {},
    onBtn1Click: () -> Unit = {},
    onBtn2Click: () -> Unit = {},
    textOnBtn2: String = "Quit being Admin",
    name: String = "name",
    age: Int = 20,
    gender: String = "Male",
    aadhar: Long = 999999999999,
    pan: String = "HLXPD1206H"

) {


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Column {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp), verticalArrangement = Arrangement.SpaceEvenly
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
                        verticalArrangement = Arrangement.spacedBy(20.dp),
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
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = name.capitalize(),
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
                                    text = age.toString(),
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
                                    text = gender,
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
                                    text = aadhar.toString(),
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
                                    text = pan,
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

                    //

                }

                Column(modifier=Modifier.fillMaxWidth().fillMaxHeight(.5f),verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {


                    Card(modifier = Modifier
                        .clickable { onBtn2Click }
                        .size(height = 55.dp, width = 300.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(
                            pressedElevation = 20.dp,
                            defaultElevation = 30.dp
                        ),
                        colors = CardDefaults.cardColors(ButtonBackground)) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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
                    Card(modifier = Modifier
                        .clickable { onBtn1Click }
                        .size(height = 55.dp, width = 300.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(
                            pressedElevation = 20.dp,
                            defaultElevation = 30.dp
                        ),
                        colors = CardDefaults.cardColors(ButtonBackground)) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
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
                }

            }

        }


    }

}