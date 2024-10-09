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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import kotlinx.coroutines.delay

@Preview(
    showBackground = true,
    apiLevel = 33 // or any lower API level, such as 33 or 32
)
@Composable
public fun list_of_poll(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    heading: String = "Previous Polls",
    subHeading: String = "Your vote Matters",
    textOnbtn: String = "Upcoming Polls",
    navController: NavHostController = rememberNavController(),
    list: List<Poll> = mutableListOf(
        Poll(
            _pollId = 100010001000.1000,
            _pollCreatedBy = 100010001000,
            _agendaOfPoll = "pollAgenda",
            _eligibleVoterAge = 20,
            _startTime = 1633036800000,
            _endTime = 1633036899999
        ),
        Poll(
            _pollId = 100010001000.1000,
            _pollCreatedBy = 100010001000,
            _agendaOfPoll = "pollAgenda",
            _eligibleVoterAge = 20,
            _startTime = 1633036800000,
            _endTime = 1633036899999
        ),
        Poll(
            _pollId = 100010001000.1000,
            _pollCreatedBy = 100010001000,
            _agendaOfPoll = "pollAgenda",
            _eligibleVoterAge = 20,
            _startTime = 1633036800000,
            _endTime = 1633036899999
        ),
        Poll(
            _pollId = 100010001000.1000,
            _pollCreatedBy = 100010001000,
            _agendaOfPoll = "pollAgenda",
            _eligibleVoterAge = 20,
            _startTime = 1633036800000,
            _endTime = 1633036899999
        ),
        Poll(
            _pollId = 100010001000.1000,
            _pollCreatedBy = 100010001000,
            _agendaOfPoll = "pollAgenda",
            _eligibleVoterAge = 20,
            _startTime = 1633036800000,
            _endTime = 1633036899999
        )
    ),
    method:String="prev_poll_user_participated"
) {
    var  listComing: MutableList<Poll> = mutableListOf()
    LaunchedEffect(true) {
        if(method=="prev_poll_user_participated"){
            listComing=allSingeltonObjects.profileViewModel.getPreviousPolls()
        }else if(method=="upcoming_poll_user"){
            listComing=allSingeltonObjects.profileViewModel.getUpcomingPolls()
        }else if(method=="prev_poll_admin_created"){
            listComing=allSingeltonObjects.profileViewModel.getPrevPollsCreated()
        }else if(method=="active_poll_admin_created"){
            listComing=allSingeltonObjects.profileViewModel.getPollsCreated()
        }

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
                .padding(start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(80.dp), horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.raise_hand),
                    contentDescription = "ballot icon",
                    Modifier.padding(start = 10.dp, top = 10.dp)

                )
            }
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = heading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp,
                color = TextOnBackgroundDark

            )
            Text(
                text =subHeading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                color = TextOnBackgroundLight

            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.8f)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = TextFieldBackground),
                border = BorderStroke(width = 2.dp, color = TextOnBackgroundDark)
            ) {
                /*
                methods-
                prev_poll_user_participated
                upcoming_poll_user
                prev_poll_admin_created
                active_poll_admin_created
                 */

                if(listComing.isEmpty()){
                    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        var t  by remember {
                            mutableStateOf("Loading...")
                        }
                        fun sett(a:String){
                            t=a
                        }

                        LaunchedEffect(key1 = true) {
                            delay(8000)
                            sett("No Polls Available")
                        }
                        Text(text = t, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = TextOnBackgroundDark)
                    }

                }else{
                    if(method=="prev_poll_user_participated"){

                        LazyColumn(
                        ) {
                            items(listComing){pollItem ->
                                each_poll_item_prev_poll_user(modifier = Modifier.height(150.dp),pollItem = pollItem, navController = navController)
                            }

                        }

                    }else if(method=="upcoming_poll_user"){
                        LazyColumn(
                        ) {
                            items(listComing){pollItem ->
                                each_poll_item_upcoming_poll(modifier = Modifier.height(150.dp),pollItem = pollItem, navController = navController)
                            }

                        }

                    }else if(method=="prev_poll_admin_created"){
                        LazyColumn(
                        ) {
                            items(listComing){pollItem ->
                                each_poll_item_prev_poll_admin(modifier = Modifier.height(150.dp),pollItem = pollItem, navController = navController)
                            }

                        }

                    }else if(method=="active_poll_admin_created"){
                        LazyColumn(
                        ) {
                            items(listComing){pollItem ->
                                each_poll_item_current_poll_admin(modifier = Modifier.height(150.dp),pollItem = pollItem, navController = navController)
                            }

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
                    .clickable { navController.popBackStack() }
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