package com.example.pollcreator.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.R
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBackgroundLight
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextFieldBackgroundLight
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import com.example.pollcreator.viewModel.createPollViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun create_poll(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    onCreateButton: () -> Unit = {},
    navController: NavController = rememberNavController()
) {
    allSingeltonObjects.createPollViewModel = createPollViewModel()

    val name by allSingeltonObjects.createPollViewModel.name
    val agenda by allSingeltonObjects.createPollViewModel.agenda
    val date by allSingeltonObjects.createPollViewModel.date
    val startingTime by allSingeltonObjects.createPollViewModel.startingTime
    val endingTime by allSingeltonObjects.createPollViewModel.endingTime
    val reqAge by allSingeltonObjects.createPollViewModel.reqAge
    val context = LocalContext.current

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
                    onValueChange = { allSingeltonObjects.createPollViewModel.setName(it) },
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
                    onValueChange = { allSingeltonObjects.createPollViewModel.setAgenda(it) },
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
                    onValueChange = { allSingeltonObjects.createPollViewModel.setDate(it) },
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
                            text = "Date of the poll     (dd/mm/yyyy)",
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),


                    )




                TextField(
                    value = startingTime,
                    onValueChange = { allSingeltonObjects.createPollViewModel.setStartingTime(it) },
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
                            text = "Starting time of the poll   (hh:mm)",
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
                    onValueChange = { allSingeltonObjects.createPollViewModel.setEndingTime(it) },
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
                            text = "Ending time of poll   (hh:mm)",
                            color = TextOnBackgroundDark,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )

                )



                TextField(
                    value = reqAge,
                    onValueChange = { allSingeltonObjects.createPollViewModel.setReqAge(it) },
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
                            text = "Required minimum age for the poll",
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
                        .clickable {
                            //check for the any empty fields , for the time that is provided for each poll atleast 5 min gap between starting and ending time
                            if (name.isEmpty() || agenda.isEmpty() || date.isEmpty() || startingTime.isEmpty()
                                || endingTime.isEmpty() || reqAge.isEmpty()
                            ) {
                                Toast
                                    .makeText(
                                        context,
                                        "Please fill all the fields",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()

                            } else {
                                if (startingTime.length != 5 || endingTime.length != 5 ||
                                    (startingTime
                                        .substring(0, 2)
                                        .toInt() > 24 &&
                                            startingTime
                                                .substring(0, 2)
                                                .toInt() < 0 ||
                                            endingTime
                                                .substring(0, 2)
                                                .toInt()> 24  ||
                                            endingTime
                                                .substring(0, 2)
                                                .toInt() < 0 ||
                                            startingTime
                                                .substring(3, 5)
                                                .toInt() > 59  ||
                                            startingTime
                                                .substring(3, 5)
                                                .toInt() < 0  ||
                                            endingTime
                                                .substring(3, 5)
                                                .toInt() > 59  ||
                                            endingTime
                                                .substring(3, 5)
                                                .toInt() < 0)
                                ) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please enter time in 24hr format eg.(15:30)",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                                if (date.length != 10 || allSingeltonObjects.helperFunctions.convertDateToUnix(
                                        date
                                    ) == 0L
                                ) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please date in dd/mm/yyyy format eg.(15/11/2024)",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                                if (reqAge.toInt() < 0 || reqAge.toInt() > 120) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please enter valid age",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()

                                }
                                if (
                                    allSingeltonObjects.helperFunctions.convertDateTimeToUnix(
                                        date,
                                        startingTime
                                    ) == 0L ||
                                    allSingeltonObjects.helperFunctions.convertDateTimeToUnix(
                                        date,
                                        endingTime
                                    ) == 0L ||
                                    allSingeltonObjects.helperFunctions.convertDateTimeToUnix(
                                        date,
                                        startingTime
                                    ) + 300000 > allSingeltonObjects.helperFunctions.convertDateTimeToUnix(
                                        date,
                                        endingTime
                                    )
                                ) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please have atleast 5 min difference between starting and ending time",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }

                                // checked for all the anomolies and now we can create the poll
                                Log.d(
                                    "aadharNO",
                                    "${
                                        allSingeltonObjects.profileViewModel.aadharNo.value
                                            .toString()
                                            .toDouble()
                                    }"
                                )
                                Log.d(
                                    "noOfPollCreated",
                                    "${allSingeltonObjects.profileViewModel.noOfPollCreated.value.toLong()}"
                                )
                                allSingeltonObjects.profileViewModel.setNoOfPollCreated(allSingeltonObjects.profileViewModel.noOfPollCreated.value +1)
                                allSingeltonObjects.referenceToUsers.child(allSingeltonObjects.profileViewModel.aadharNo.value).child("noOfPollCreated").setValue(allSingeltonObjects.profileViewModel.noOfPollCreated.value)

                                val pollIdGet =
                                    allSingeltonObjects.profileViewModel.aadharNo.value.toString() + (
                                            allSingeltonObjects.profileViewModel.noOfPollCreated.value.toLong()).toString()

                                Log.d("poll Id", "$pollIdGet")
                                val pollToCreate = Poll(
                                    _name = name,
                                    _pollId = pollIdGet,
                                    _pollCreatedBy = allSingeltonObjects.profileViewModel.aadharNo.value,
                                    _agendaOfPoll = agenda,
                                    _eligibleVoterAge = reqAge.toInt(),
                                    _startTime = allSingeltonObjects.helperFunctions.convertDateTimeToUnix(
                                        date,
                                        startingTime
                                    ),
                                    _endTime = allSingeltonObjects.helperFunctions.convertDateTimeToUnix(
                                        date,
                                        endingTime
                                    )
                                )
                                allSingeltonObjects.createPollViewModel = createPollViewModel()
                                allSingeltonObjects.createPollViewModel.createPoll(
                                    context,
                                    pollToCreate
                                )
                                // updating all the firebase table are done by createPollViewModel and Web3jDataModel

                                Toast
                                    .makeText(
                                        context,
                                        "Poll created successfully",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                navController.popBackStack()

                            }
                        }
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
                    .clickable { navController.navigate("prevPollUserParticipated") }
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
                        .clickable { navController.navigate("profile") }

                )


            }

        }

    }

}