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
public fun admin_registration  (
    modifier: Modifier = Modifier,
    onBtn1Click: () -> Unit = {},
    onBtn1FailClick: () -> Unit = {},
    currentPassword : String = ""

) {

    var password by remember {
        mutableStateOf("")
    }
    var pan by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember { mutableStateOf(false) }


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
                    text = "Enter your additional details",
                    fontSize = 38.sp,
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
                        .fillMaxHeight(.4f),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {



                        TextField(
                            value = pan,
                            onValueChange = { pan = it },
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = TextFieldBackground,
                                focusedTextColor = TextOnBackgroundDark,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = TextOnBackgroundLight,
                                unfocusedTextColor = TextOnBackgroundDark
                            ),
                            singleLine = true,

                            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.mdi_age),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 3.dp, end = 11.dp)
                                        .size(32.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Enter your pan no.",
                                    color = TextOnBackgroundDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )

                        )



                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = TextFieldBackground,
                                focusedTextColor = TextOnBackgroundDark,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = TextOnBackgroundLight,
                                unfocusedTextColor = TextOnBackgroundDark
                            ),
                            singleLine = true,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.mdi_password),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 16.dp, bottom = 3.dp, end = 11.dp)
                                        .size(32.dp)
                                )
                            },
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    painterResource(id = R.drawable.baseline_visibility_24)
                                else painterResource(id = R.drawable.baseline_visibility_off_24)

                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = image,
                                        contentDescription = description,
                                        modifier = Modifier.padding(end = 10.dp),
                                        tint = Color.White
                                    )

                                }
                            },
                            placeholder = {
                                Text(
                                    text = "Password",
                                    color = TextOnBackgroundDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            )

                        )







                    }

                    //

                }

                Column(modifier= Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f),verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {


                    Card(modifier = Modifier
                        .clickable { if (password.equals(currentPassword))onBtn1Click else onBtn1FailClick }
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
                                text = "Confirm",
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