package com.example.pollcreator.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun user_each_poll_clicked(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    callDialog: () -> Unit = {},
    onFailBtn: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    poll_title: String = "National Elections",
    currentPassword: String = "",
    isPollOngoing: Boolean = true,
    aadharno1: Long = 333335555555
) {


    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember { mutableStateOf(false) }

    var selectedCandidateAadhar by remember {
        mutableStateOf<Long?>(null)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // add here the parameters for the prev vote list and show here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp, top = 10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(120.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {


                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "Poll Details",
                            modifier = Modifier
                                .padding(start = 10.dp),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp,
                            color = TextOnBackgroundDark

                        )


                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.vote_image),
                    contentDescription = "ballot icon",
                    Modifier.size(width = 167.dp, height = 180.dp)

                )
            }

            each_poll_item(modifier = Modifier.height(180.dp))



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.50f)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = TextFieldBackground),
                border = BorderStroke(width = 2.dp, color = TextOnBackgroundDark)
            ) {
                Card(
                    Modifier.verticalScroll(rememberScrollState()),
                    colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent)
                ) {
                    Column {

                        ///  apply here the list of the participant and take them as input in a list and make them show in a loop and also
                        ///          make a if condition where if the isPollOngoing is true then the radiobutton will show else it wont


                        Row(verticalAlignment = Alignment.CenterVertically) {

                            RadioButton(
                                selected = if (selectedCandidateAadhar == aadharno1) true else false,
                                onClick = { selectedCandidateAadhar = aadharno1 },

                                colors = RadioButtonColors(
                                    selectedColor = Color.White,
                                    unselectedColor = Color.Black,
                                    disabledUnselectedColor = Color.Transparent,
                                    disabledSelectedColor = Color.White
                                )
                            )
                            each_participant_in_poll_clicked()


                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = if (selectedCandidateAadhar == aadharno1) true else false,
                                onClick = { selectedCandidateAadhar = aadharno1 },
                                colors = RadioButtonColors(
                                    selectedColor = Color.White,
                                    unselectedColor = Color.Black,
                                    disabledUnselectedColor = Color.Transparent,
                                    disabledSelectedColor = Color.White
                                )
                            )
                            each_participant_in_poll_clicked()


                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = if (selectedCandidateAadhar == aadharno1) true else false,
                                onClick = { selectedCandidateAadhar = aadharno1 },
                                colors = RadioButtonColors(
                                    selectedColor = Color.White,
                                    unselectedColor = Color.Black,
                                    disabledUnselectedColor = Color.Transparent,
                                    disabledSelectedColor = Color.White
                                )
                            )
                            each_participant_in_poll_clicked()


                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = if (selectedCandidateAadhar == aadharno1) true else false,
                                onClick = { selectedCandidateAadhar = aadharno1 },
                                colors = RadioButtonColors(
                                    selectedColor = Color.White,
                                    unselectedColor = Color.Black,
                                    disabledUnselectedColor = Color.Transparent,
                                    disabledSelectedColor = Color.White
                                )
                            )
                            each_participant_in_poll_clicked()


                        }

                    }


                }


            }

            Spacer(modifier = Modifier.size(15.dp))
            if (isPollOngoing) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = password,
                        onValueChange = { password = it },
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

                    Spacer(modifier = Modifier.size(10.dp))

                    Card(modifier = Modifier
                        .clickable { if (password.equals(passwordVisible)) callDialog() else onFailBtn() }
                        .size(height = 50.dp, width = 250.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(
                            pressedElevation = 20.dp,
                            defaultElevation = 30.dp
                        ),
                        colors = CardDefaults.cardColors(containerColor = TextFieldBackground)) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Confirm Your Vote",                           // add here a dialog box for confirmation
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextOnBackgroundDark,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Card(
                        modifier = Modifier
                            .size(height = 50.dp, width = 250.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(
                            pressedElevation = 20.dp,
                            defaultElevation = 30.dp
                        ),
                        colors = CardDefaults.cardColors(containerColor = TextFieldBackground)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Come back Later",                           // add here a dialog box for confirmation
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextOnBackgroundDark,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }


            }






            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(modifier = Modifier
                    .clickable { onPrevVoteButton }
                    .size(height = 60.dp, width = 200.dp),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(width = 2.dp, color = CardBorderDark),
                    elevation = CardDefaults.cardElevation(
                        pressedElevation = 4.dp,
                        defaultElevation = 2.dp
                    ),
                    colors = CardDefaults.cardColors(containerColor = ButtonBackground)) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Previous Vote",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )

                    }

                }
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "ballot icon",
                    Modifier
                        .padding(end = 30.dp)
                        .size(50.dp)
                        .clickable { onProfileButton }

                )


            }

        }

    }

}