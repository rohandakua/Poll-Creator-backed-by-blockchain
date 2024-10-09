package com.example.pollcreator.screens


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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.R
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun show_candidate_list_for_vote(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    callDialog: () -> Unit = {},
    onFailBtn: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    poll_title: String = "National Elections",
    currentPassword: String = "",
    aadharno1: Long = 333335555555,
    navController: NavHostController = rememberNavController()
) {

    val context = LocalContext.current
    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember { mutableStateOf(false) }

    var selectedCandidateAadhar by remember {
        mutableStateOf<Long?>(null)
    }

    var poll = allSingeltonObjects.profileViewModel.getPollItem() ?: Poll(
        _pollId = 123412341234.12,
        _pollCreatedBy = 123412341234,
        _agendaOfPoll = "pollAgenda",
        _eligibleVoterAge = 20,
        _startTime = 1633036800000,
        _endTime = 1633036899999
    )
    var listOfCandidates = poll._listOfCandidate
    var isPollOngoing =
        if (allSingeltonObjects.helperFunctions.convertToUnixTimestamp(Date()) > poll._startTime && allSingeltonObjects.helperFunctions.convertToUnixTimestamp(
                Date()
            ) < poll._endTime
        ) true else false

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

            each_poll_item_upcoming_poll(
                modifier = Modifier.height(180.dp),
                pollItem = allSingeltonObjects.profileViewModel.getPollItem() ?: Poll(
                    _pollId = 123412341234.12,
                    _pollCreatedBy = 123412341234,
                    _agendaOfPoll = "pollAgenda",
                    _eligibleVoterAge = 20,
                    _startTime = 1633036800000,
                    _endTime = 1633036899999
                )
            )



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.50f)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = TextFieldBackground),
                border = BorderStroke(width = 2.dp, color = TextOnBackgroundDark)
            ) {


                LazyColumn {
                    items(listOfCandidates) { item ->
                        RadioButton(
                            selected = selectedCandidateAadhar == item.candidate._aadharNo,
                            onClick = { selectedCandidateAadhar = item.candidate._aadharNo },

                            colors = RadioButtonColors(
                                selectedColor = Color.White,
                                unselectedColor = Color.Black,
                                disabledUnselectedColor = Color.Transparent,
                                disabledSelectedColor = Color.White
                            )
                        )
                        each_participant_in_poll_detailed(pollResultObj = item)

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
                        .clickable {
                            if (allSingeltonObjects.profileViewModel.password.equals(password)) {
                                if (selectedCandidateAadhar.toString().length != 12) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please select a candidate",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else {
                                    //make the call to cast vote
                                    allSingeltonObjects.pollViewModel.castVote(
                                        aadharNoOfCandidate = selectedCandidateAadhar
                                            ?: 123412341234,
                                        gender = allSingeltonObjects.profileViewModel.gender.value.toString()
                                            ?: "male",
                                        aadharNoOfVoter = allSingeltonObjects.profileViewModel.aadharNo.value.toLong(),
                                        password = password,
                                        pollId = poll._pollId
                                    )
                                }


                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Your password is incorrect",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()

                            }
                        }
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
                        .clickable {
                            navController.navigate("profile")
                        }

                )


            }

        }

    }

}