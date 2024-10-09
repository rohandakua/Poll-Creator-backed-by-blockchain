package com.example.pollcreator.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.dataclass.Candidate
import com.example.pollcreator.dataclass.Gender
import com.example.pollcreator.dataclass.PollResultObj
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark


@Preview(device = "spec:width=450dp,height=150dp,dpi=480")
@Composable
public fun each_participant_in_poll_detailed(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    pollResultObj: PollResultObj=PollResultObj(
        candidate = Candidate(
            _pollId = 100110011001.1001,
            100110011001,
            20,
            Gender.MALE,
            1000000,
            "hello lorem ipsum"
        ),
        noOfVote = 10000
    )
) {
    val candidateItem=pollResultObj.candidate
    val name: String = candidateItem._name
    val agenda: String = candidateItem._agenda
    val gender: String = if(candidateItem._gender == Gender.MALE) "Male" else "Female"
    val disclosedIncome: Long = candidateItem._yearlyIncome
    val age: Int = candidateItem._age


    Box(
        modifier = modifier
            .fillMaxSize(), contentAlignment = Alignment.Center

    ) {
        Card(
            modifier = Modifier
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
                        text = capitalizeEachWord(name),
                        fontWeight = FontWeight.SemiBold,
                        color = TextOnBackgroundDark,
                        maxLines = 1,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 7.dp)
                    )
                }
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = agenda,
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 4.dp)
                            .weight(1.25f),
                        maxLines = 4,
                        fontSize = 16.sp,
                        color = TextOnBackgroundDark, textAlign = TextAlign.Justify
                    )

                    Column(
                        Modifier
                            .weight(1f).fillMaxHeight()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = age.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )

                        Text(
                            text = gender.capitalize(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "₹ "+disclosedIncome.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )

                    }


                }

            }

        }


    }

}