package com.example.pollcreator.screens

import android.util.Log
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.R
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.onlineStorage.fireBaseDataModel
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import com.example.pollcreator.viewModel.signInViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login_page(
    modifier: Modifier = Modifier,
    isAdminFromSuper: Boolean = true,
    isLoginFromSuper: Boolean,

    navController: NavController = rememberNavController()
) {
    val viewModel : signInViewModel = allSingeltonObjects.signInViewModel

    val isLogin by viewModel.isLogin
    val isAdmin by viewModel.isAdmin

    viewModel.setIsLogin(isLoginFromSuper)
    viewModel.setIsAdmin(isAdminFromSuper)
    val isSuccess by viewModel.isSuccess

    var MainHeading: String = if (isLogin) "Login" else "Create Account"
    var SubHeading: String =
        if (isLogin) "Please sign in to continue" else "Please fill in the details below"
    var isName: Boolean = if (isLogin) false else true
    var isAge: Boolean = if (isLogin) false else true
    var isPan: Boolean = isAdmin
    var passwordVisible by remember { mutableStateOf(false) }

    val aadharNo by viewModel.aadharNo
    val password by viewModel.password
    val age by viewModel.age
    val gender by viewModel.gender
    val name by viewModel.name
    val panNo by viewModel.panNo


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
                            onValueChange = { viewModel.setName(it) },
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
                            onValueChange = { viewModel.setAge(it.toIntOrNull()?: 0) },
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
                                    selected = if (gender.equals("male")) true else false,
                                    onClick = { viewModel.setGender("male") },
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
                                    onClick = { viewModel.setGender("female") },
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
                            value = panNo ?: "",
                            onValueChange = { viewModel.setPanNo(it) },
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
                                keyboardType = KeyboardType.Text
                            )

                        )
                    }


                    TextField(
                        value = aadharNo?.toString() ?: "",
                        onValueChange = { viewModel.setAadharNo(it.toLongOrNull()?: 0L) },
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
                        onValueChange = { viewModel.setPassword(it) },
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
                        .clickable {
                            Log.d("buttonClickedOrNot", "clickeddddd")
                            allSingeltonObjects.profileViewModel.getCopyOfDetailsFromSignIn()




                            if (isLogin && !isAdmin) {
                                // user login
                                Log.d("signIn_admin", "registering admin")
                                viewModel.viewModelScope.launch {
                                    viewModel.signInUser()
                                }

                                Log.d("after viewmodel register user", "called")
                            } else if (isLogin && isAdmin) {
                                // admin login
                                Log.d("register_admin", "registering admin")
                                viewModel.viewModelScope.launch {
                                    viewModel.signInAdmin()
                                }
                                Log.d("after viewmodel register user", "called")
                            } else if (!isLogin && !isAdmin) {
                                //register user
                                Log.d("register_admin", "registering admin")
                                viewModel.viewModelScope.launch {
                                    viewModel.registerUser()
                                }
                                Log.d("after viewmodel register user", "called")
                            } else if (!isLogin && isAdmin) {
                                // register admin
                                Log.d("register_admin", "registering admin")
                                viewModel.viewModelScope.launch {
                                    viewModel.registerAdmin()
                                }
                                Log.d("after viewmodel register user", "called")
                            }




                            if (isSuccess && !isAdmin) {
                                // use nav controller to go to next screen and remove that from backstack

                                navController.navigate("successAnimation/${isSuccess}")

                            } else if (isSuccess && isAdmin) {
                                navController.navigate("successAnimation/${isSuccess}")
                            } else {
                                navController.navigate("successAnimation/${isSuccess}")
                            }

                        }
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
                            onClick = {
                                if (isLogin) navController.navigate("loginOrSignUp/${isAdmin}/${!isLogin}") else navController.navigate(
                                    "loginOrSignUp/${isAdmin}/${!isLogin}"
                                )
                            },
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