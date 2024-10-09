package com.example.pollcreator.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.viewModel.pollViewModel
import java.util.Date


@Preview(device = "spec:width=450dp,height=150dp,dpi=480",
showBackground = true,
apiLevel = 33 // or any lower API level, such as 33 or 32
)
@Composable
public fun each_poll_item_upcoming_poll(
    modifier: Modifier = Modifier,

    onClick : ()->Unit = {},
    pollId:Double=100020003000.1,
    navController: NavHostController = rememberNavController(),
    pollItem : Poll = Poll(
        _pollId = 123412341234.12,
        _pollCreatedBy = 123412341234,
        _agendaOfPoll = "pollAgenda",
        _eligibleVoterAge = 20,
        _startTime = 1633036800000,
        _endTime = 1633036899999

    )
) {
    val pollName: String = pollItem._name
    val pollAgenda: String = pollItem._agendaOfPoll
    val startTime: String = allSingeltonObjects.helperFunctions.getDateFromTimestamp(pollItem._startTime)
    val endTime: String = allSingeltonObjects.helperFunctions.getDateFromTimestamp(pollItem._endTime)

    // making changes in the profileViewModel for storing the poll Item
    allSingeltonObjects.profileViewModel.setPollItem(pollItem)
    //making changes in the pollViewModel
    allSingeltonObjects.pollViewModel = pollViewModel(pollItem._pollId)



    Box(
        modifier = modifier
            .fillMaxSize(), contentAlignment = Alignment.Center

    ) {
        Card(
            modifier = Modifier.clickable {
                // check for the time and then decide what to do
                val currentTime : Long = allSingeltonObjects.helperFunctions.convertToUnixTimestamp(Date())
                if(currentTime<pollItem._startTime){
                    // this means the poll has no yet been started , route to show_candidate_list
                    navController.navigate("show_candidate_list")


                }else if(currentTime>pollItem._endTime){
                    // this means the poll has ended  , route to the poll result screen
                    navController.navigate("poll_result")

                }else{
                    // the poll is active and the user can vote , route to the show_candidate_list_for_vote
                    navController.navigate("show_candidate_list_for_vote")
                }



            }
                .fillMaxSize(.96f)
                .padding(4.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MainBackground),
            border = BorderStroke(width = 4.dp, color = CardBorderDark),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pollName.uppercase(),
                        fontWeight = FontWeight.SemiBold,
                        color = TextOnBackgroundDark,
                        maxLines = 1,
                        fontSize = 25.sp,

                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 7.dp)
                    )
                }
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = pollAgenda,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1.25f),
                        maxLines = 4,
                        fontSize = 16.sp,
                        color = TextOnBackgroundDark, textAlign = TextAlign.Justify
                    )

                    Column(
                        Modifier
                            .weight(1f)
                            .padding(4.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = startTime.substring(0,11),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )

                        Text(
                            text = startTime.substring(13)+" to " + endTime.substring(13),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )

                    }


                }

            }

        }


    }

}