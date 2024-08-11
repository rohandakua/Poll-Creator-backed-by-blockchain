package com.example.pollcreator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight

@Preview
@Composable
private fun viewUserLogin() {
    login_page(
        MainHeading = "Login",
        SubHeading = "Please sign in to continue",
        isLogin = true,
        isName = false,
        isAge = false,
        isPan = false
    )

}

@Preview
@Composable
private fun viewAdminLogin() {
    login_page(
        MainHeading = "Login",
        SubHeading = "Please sign in to continue",
        isLogin = true,
        isName = false,
        isAge = false,
        isPan = true
    )

}

@Preview
@Composable
private fun viewUserRegister() {
    login_page(
        MainHeading = "Create Account",
        SubHeading = "Please fill in the details below",
        isLogin = false,
        isName = true,
        isAge = true,
        isPan = false
    )

}

@Preview
@Composable
private fun viewAdminRegister() {
    login_page(
        MainHeading = "Create Account",
        SubHeading = "Please fill in the details below",
        isLogin = false,
        isName = true,
        isAge = true,
        isPan = true
    )

}

@Preview
@Composable
private fun bb(){
    Box(modifier = Modifier.fillMaxSize(). background(color = Color.Red)){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF8FBC8F), contentColor = Color.White , disabledContentColor = Color.Red, disabledContainerColor = Color.Cyan ),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp, pressedElevation = 2.dp, focusedElevation = 8.dp)
        ) {
            // Content of the card goes here
            // For this example, it's just an empty green surface
        }
    }

    
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login_page(
    modifier: Modifier = Modifier,
    MainHeading: String,
    SubHeading: String,
    isLogin: Boolean,
    isName: Boolean,
    isAge: Boolean,
    isPan: Boolean,
    onMainBtnClick: () -> Unit = {},
    onLastBtnClick: () -> Unit = {}
) {
    var aadharNo by remember {
        mutableStateOf<Long?>(null)
    }
    var password by remember {
        mutableStateOf("")
    }

    var panNo by remember {
        mutableStateOf("")
    }


    var name by remember {
        mutableStateOf("")
    }


    var age by remember {
        mutableStateOf<Int?>(null)
    }

    var passwordVisible by remember { mutableStateOf(false) }


    var gender by remember {
        mutableStateOf("")
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Column {
            if (isLogin) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.size(140.dp))
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = R.drawable.loginimage),
                        contentDescription = "login image "
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(70.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(14.dp)
            ) {

                Text(
                    text = MainHeading,
                    fontSize = 32.sp,
                    color = com.example.pollcreator.ui.theme.TextOnBackgroundDark,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = SubHeading,
                    fontSize = 16.sp,
                    color = com.example.pollcreator.ui.theme.TextOnBackgroundLight,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.size(20.dp))
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isName) {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            modifier = Modifier.fillMaxWidth(),
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
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.mdi_user),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 18.dp, bottom = 3.dp, end = 15.dp)
                                        .size(30.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Name",
                                    color = TextOnBackgroundDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            )

                        )
                    }

                    if (isAge) {
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
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.mdi_age),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 20.dp, bottom = 3.dp, end = 18.dp)
                                        .size(24.dp)
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
                    }
                    if (!isLogin) {
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
                                    selected = if(gender.equals("male")) true else false,
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
                                    selected = if(gender.equals("female")) true else false,
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

                    }


                    if (isPan) {
                        TextField(
                            value = panNo,
                            onValueChange = { panNo = it },
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
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            leadingIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.mdi_age),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(start = 20.dp, bottom = 3.dp, end = 18.dp)
                                        .size(24.dp)
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "PAN Number",
                                    color = TextOnBackgroundDark,
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            )

                        )
                    }


                    TextField(
                        value = aadharNo?.toString() ?: "",
                        onValueChange = { aadharNo = it.toLongOrNull() },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(25.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldBackground,
                            focusedTextColor = TextOnBackgroundDark,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = TextOnBackgroundLight
                        ),
                        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                        leadingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.arcticons_maadhaar),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 12.dp, bottom = 3.dp, end = 1.dp)
                                    .size(48.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Aadhaar Number",
                                color = TextOnBackgroundDark,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
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
                    Spacer(modifier = Modifier.size(40.dp))

                    //
                    Card(modifier = Modifier
                        .clickable { onMainBtnClick }
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
                                text = if (isLogin) "Login" else "Register",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = if (isLogin) "Don't have an account? " else "Already have an account? ",
                            color = TextOnBackgroundDark
                        )
                        Button(
                            onClick = { onLastBtnClick },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = if (isLogin) "Register" else "Login",
                                color = TextOnBackgroundDark,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

            }

        }


    }

}