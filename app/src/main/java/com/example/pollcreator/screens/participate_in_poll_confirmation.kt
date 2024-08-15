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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
public fun participate_in_poll_confirmation(
    modifier: Modifier = Modifier,
    onParticipateBtnClick: () -> Unit = {}
) {
    var aadharNo by remember {
        mutableStateOf<Long?>(null)
    }
    var password by remember {
        mutableStateOf("")
    }


    var passwordVisible by remember { mutableStateOf(false) }

    var agenda by remember {
        mutableStateOf("")
    }


    var yearlyIncome by remember {
        mutableStateOf<Long?>(null)
    }


    var age by remember {
        mutableStateOf<Int?>(null)
    }


    var gender by remember {
        mutableStateOf("")
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            textDecoration = TextDecoration.Underline,
                            fontSize = 32.sp,
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.ExtraBold // Make the text bold to simulate a bolder underline
                        )
                    ) {
                        append("Confirm your Details")
                    }
                }
            )


            //


            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackgroundLight),
                modifier = Modifier
                    .fillMaxWidth(.98f)
                    .fillMaxHeight(.6f),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {


                //

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = aadharNo?.toString() ?: "",
                        onValueChange = { aadharNo = it.toLongOrNull() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        shape = RoundedCornerShape(25.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldBackground,
                            focusedTextColor = TextOnBackgroundDark,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = TextOnBackgroundDark,
                            unfocusedTextColor = TextOnBackgroundDark


                        ),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.arcticons_maadhaar),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 15.dp, bottom = 3.dp, end = 5.dp)
                                    .size(42.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Aadhar Number",
                                color = TextOnBackgroundDark,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )

                    )


                    TextField(
                        value = age?.toString() ?: "",
                        onValueChange = { age = it.toIntOrNull() },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldBackground,
                            focusedTextColor = TextOnBackgroundDark,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = TextOnBackgroundLight,
                            unfocusedTextColor = TextOnBackgroundDark
                        ),
                        textStyle = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.mdi_age),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 18.dp, bottom = 3.dp, end = 13.dp)
                                    .size(30.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Age",
                                color = TextOnBackgroundDark,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )

                    )
                    Text(
                        text = "Select Gender",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextOnBackgroundDark,
                        textAlign = TextAlign.Center
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.map_male),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )

                        Card(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            colors = CardDefaults.cardColors(containerColor = TextFieldBackground),
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            RadioButton(
                                selected = if (gender.equals("male")) true else false,
                                onClick = { gender = "male" },
                                colors = RadioButtonColors(
                                    selectedColor = Color.White,
                                    unselectedColor = Color.Transparent,
                                    disabledUnselectedColor = Color.Transparent,
                                    disabledSelectedColor = Color.White
                                )
                            )
                        }
                        Spacer(modifier = Modifier.size(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.map_female),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )



                        Card(
                            modifier = Modifier.size(width = 30.dp, height = 30.dp),
                            colors = CardDefaults.cardColors(containerColor = TextFieldBackground),
                            elevation = CardDefaults.cardElevation(20.dp)
                        ) {
                            RadioButton(
                                selected = if (gender.equals("female")) true else false,
                                onClick = { gender = "female" },
                                colors = RadioButtonColors(
                                    selectedColor = Color.White,
                                    unselectedColor = Color.Transparent,
                                    disabledUnselectedColor = Color.Transparent,
                                    disabledSelectedColor = Color.Transparent
                                )
                            )
                        }


                    }

                    TextField(
                        value = yearlyIncome?.toString() ?: "",
                        onValueChange = { yearlyIncome = it.toLongOrNull() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldBackground,
                            focusedTextColor = TextOnBackgroundDark,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = TextOnBackgroundLight,
                            unfocusedTextColor = TextOnBackgroundDark
                        ),
                        textStyle = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_currency_rupee_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 18.dp, bottom = 3.dp, end = 10.dp)
                                    .size(36.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Yearly Income",
                                color = TextOnBackgroundDark,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )

                    )



                    TextField(
                        value = agenda,
                        onValueChange = { agenda = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldBackground,
                            focusedTextColor = TextOnBackgroundDark,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = TextOnBackgroundLight
                        ),
                        textStyle = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.mdi_age),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 18.dp, bottom = 3.dp, end = 13.dp)
                                    .size(30.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Agenda",
                                color = TextOnBackgroundDark,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )

                    )
                    TextField(
                        value = password?.toString() ?: "",
                        onValueChange = { password = it.toString() },
                        modifier = Modifier.fillMaxWidth(),
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
                        textStyle = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
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
            Card(modifier = Modifier
                .clickable { onParticipateBtnClick }
                .size(height = 55.dp, width = 200.dp),
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
                        text = "Participate",
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