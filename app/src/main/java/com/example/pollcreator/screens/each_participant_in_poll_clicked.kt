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
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight


@Preview(device = "spec:width=450dp,height=150dp,dpi=480")
@Composable
public fun each_participant_in_poll_clicked(
    modifier: Modifier = Modifier,
    name: String = "mamta madarchod banerjee",
    agenda: String = "choose the prime minister of india choose the prime minister of india why not choosing",
    gender: String = "female",
    disclosedIncome: Long = 55555555555,
    age: Int = 20
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