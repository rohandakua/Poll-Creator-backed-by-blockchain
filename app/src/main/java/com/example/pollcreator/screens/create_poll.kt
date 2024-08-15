package com.example.pollcreator.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pollcreator.R
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBackgroundLight
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextFieldBackgroundLight
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun create_poll(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    onCreateButton: () -> Unit = {}
) {
    var name by remember {
        mutableStateOf("")

    }
    var agenda by remember {
        mutableStateOf("")

    }
    var date by remember {
        mutableStateOf("")

    }
    var startingTime by remember {
        mutableStateOf("")

    }
    var endingTime by remember {
        mutableStateOf("")

    }
    var reqAge by remember {
        mutableStateOf<Int?>(null)

    }





    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "  Fill in the details",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 30.dp, bottom = 15.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp,
                color = TextOnBackgroundDark

            )
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = CardBackgroundLight),
                modifier = Modifier.fillMaxWidth(.95f),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {


                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldBackgroundLight,
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


                TextField(
                    value = agenda,
                    onValueChange = { agenda = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldBackgroundLight,
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
                            painter = painterResource(id = R.drawable.mdi_age),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 18.dp, bottom = 3.dp, end = 15.dp)
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
                    value = date,
                    onValueChange = { date = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldBackgroundLight,
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
                            painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 18.dp, bottom = 3.dp, end = 15.dp)
                                .size(30.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Date of the poll",
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )

                )




                TextField(
                    value = startingTime,
                    onValueChange = { startingTime = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldBackgroundLight,
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
                            painter = painterResource(id = R.drawable.baseline_access_time_24),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 18.dp, bottom = 3.dp, end = 15.dp)
                                .size(30.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Starting time of the poll",
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )

                )


                TextField(
                    value = endingTime,
                    onValueChange = { endingTime = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldBackgroundLight,
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
                            painter = painterResource(id = R.drawable.baseline_access_time_filled_24),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 18.dp, bottom = 3.dp, end = 15.dp)
                                .size(30.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Ending time of poll",
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )

                )



                TextField(
                    value = reqAge?.toString() ?: "",
                    onValueChange = { reqAge = it.toIntOrNull() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(25.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = TextFieldBackgroundLight,
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
                            painter = painterResource(id = R.drawable.mdi_age),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(start = 18.dp, bottom = 3.dp, end = 15.dp)
                                .size(30.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Required  minimum age for the poll",
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )

                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 10.dp)
                ) {
                    Card(modifier = Modifier
                        .clickable { onCreateButton }
                        .size(height = 60.dp, width = 250.dp),
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
                                text = "Create the poll",
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
            Spacer(modifier = Modifier.size(40.dp))

            Row(
                Modifier.fillMaxWidth(.95f),
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