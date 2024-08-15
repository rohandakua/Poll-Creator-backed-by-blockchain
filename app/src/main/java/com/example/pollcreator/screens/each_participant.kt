package com.example.pollcreator.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight


fun capitalizeEachWord(text: String): String {
    if (text.isEmpty()) {
        return text
    }
    val words = text.split(" ")
    val capitalizedWords = words.map { it.capitalize() }
    return capitalizedWords.joinToString(" ")
}


@Preview(device = "spec:width=450dp,height=150dp,dpi=480")
@Composable
public fun each_participant(
    modifier: Modifier = Modifier,
    nameOfParticipant: String = "rahul damodardas modi",
    genderOfParticipant: String = "female",
    noOfVoteOfParticipant: Long =5555555555555
) {
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
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {



                }

                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = capitalizeEachWord(nameOfParticipant),
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .weight(1.5f),
                        maxLines = 2,
                        fontSize = 18.sp,
                        color = TextOnBackgroundDark
                    )

                    Column(
                        Modifier
                            .weight(.8f),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = genderOfParticipant,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )

                        Text(
                            text = noOfVoteOfParticipant.toString(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray
                        )

                    }


                }

            }

        }


    }

}