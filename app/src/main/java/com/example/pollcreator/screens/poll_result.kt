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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.pollcreator.ui.theme.CardBackgroundLight
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun poll_result(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    navController: NavHostController = rememberNavController()
    //list of the participant with their votes pass here as a parameter
) {
    //getting the poll details from pollViewModel
    val poll = allSingeltonObjects.pollViewModel.getDetailsOfPoll()
    val listOfCandidate = allSingeltonObjects.pollViewModel.getResultOfPoll()

    val title: String = poll._name
    val agenda: String = poll._agendaOfPoll
    val date: String =
        allSingeltonObjects.helperFunctions.getDateFromTimestamp(poll._startTime).substring(0, 11)
    val totalNoOfVoter: Long = poll._noOfMaleVoter + poll._noOfFemaleVoter
    val winnerName: String = listOfCandidate.first().candidate._name
    val winnerVoteCount: Long = listOfCandidate.first().noOfVote
    val time: String = allSingeltonObjects.helperFunctions.getDateFromTimestamp(poll._startTime)
        .substring(13) + " to " + allSingeltonObjects.helperFunctions.getDateFromTimestamp(poll._endTime)
        .substring(13)
    val textOnbtn: String = "Previous Vote"
    val isFinished: Boolean =   // if the poll is active or not
        if (poll._endTime < allSingeltonObjects.helperFunctions.convertToUnixTimestamp(
                Date()
            )
        ) true else false


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
                text = if (isFinished) "  Results" else "  Current Results",
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .padding(top = 8.dp, bottom = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        border = BorderStroke(width = 2.dp, color = CardBorderDark)
                    ) {
                        Column {
                            Text(
                                text = title, modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 4.dp, start = 2.dp, end = 2.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = agenda, modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 4.dp, start = 8.dp, end = 8.dp),
                                textAlign = TextAlign.Justify,
                                fontSize = 16.sp
                            )
                        }

                    }


                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .padding(top = 8.dp, bottom = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Text(
                                text = date, modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 4.dp, start = 2.dp, end = 2.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = time, modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(bottom = 4.dp, start = 8.dp, end = 8.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        }

                    }


                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .padding(top = 8.dp, bottom = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column {
                            Text(
                                text = "Total number of voters : " + totalNoOfVoter.toString(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 4.dp, start = 6.dp, end = 6.dp, bottom = 4.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 17.sp
                            )

                        }

                    }
                    Card(
                        border = BorderStroke(width = 1.dp, color = CardBorderDark),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {


                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp, start = 4.dp, top = 5.dp, bottom = 5.dp)
                        ) {

                            Text(
                                text = if (isFinished) "Winner - " else "Current Winner ",
                                modifier = Modifier
                                    .weight(.5f)
                                    .padding(top = 4.dp, start = 10.dp, end = 4.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextOnBackgroundDark
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(.2f)
                                    .weight(1f)
                            ) {


                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(top = 8.dp, bottom = 8.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = winnerName, modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally)
                                                .padding(top = 4.dp, start = 4.dp, end = 4.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp
                                        )

                                    }

                                }

                                Card(
                                    shape = RoundedCornerShape(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = TextOnBackgroundLight),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(top = 8.dp, bottom = 8.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = winnerVoteCount.toString(), modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally)
                                                .padding(top = 4.dp, start = 4.dp, end = 4.dp),
                                            textAlign = TextAlign.Center,
                                            fontSize = 18.sp, maxLines = 1
                                        )

                                    }

                                }

                            }


                        }
                    }


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.65f)
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = TextFieldBackground),
                        border = BorderStroke(width = 2.dp, color = TextOnBackgroundDark)
                    ) {
                        LazyColumn {
                            items(listOfCandidate){item ->   // pollResultObj type item
                                each_participant(modifier = Modifier.height(60.dp),
                                    pollResultObj = item,
                                    navController = navController)

                            }
                        }





                    }


                }


            }

            // dont touch here

            Row(
                Modifier
                    .fillMaxWidth(.95f)
                    .padding(top = 10.dp, bottom = 10.dp),
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
                            text = textOnbtn,
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