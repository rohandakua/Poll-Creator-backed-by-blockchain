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
import kotlinx.coroutines.delay

@Preview
@Composable
public fun participate_in_a_poll_as_participants(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
        // add here the parameters for the prev vote list and show here
        var listComing: MutableList<Poll> = mutableListOf()
        LaunchedEffect(true) {
            listComing = allSingeltonObjects.profileViewModel.getUpcmingPollsForAdminToParticipate()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "All the polls you are eligible for",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = TextOnBackgroundDark

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


                if (listComing.isEmpty()) {
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        var t by remember {
                            mutableStateOf("Loading...")
                        }

                        fun sett(a: String) {
                            t = a
                        }

                        LaunchedEffect(key1 = true) {
                            delay(8000)
                            sett("No Polls Available")
                        }
                        Text(
                            text = t,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextOnBackgroundDark
                        )
                    }

                } else {

                    LazyColumn(
                    ) {
                        items(listComing) { pollItem ->
                            each_poll_item_participate(
                                modifier = Modifier.height(150.dp),
                                pollItem = pollItem,
                                navController = navController
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