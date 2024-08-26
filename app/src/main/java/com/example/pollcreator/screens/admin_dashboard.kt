package com.example.pollcreator.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.R
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight

@Preview
@Composable
public fun admin_dashboard(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    navController: NavController = rememberNavController()
) {
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
                    .height(140.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {


                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            text = "Dashboard",
                            modifier = Modifier
                                .padding(start = 20.dp),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp,
                            color = TextOnBackgroundDark

                        )


                    }
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f), verticalArrangement = Arrangement.Bottom
                    ) {


                        Text(
                            text = "Ongoing polls",
                            modifier = Modifier
                                .padding(start = 20.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400,
                            color = TextOnBackgroundLight,
                            textAlign = TextAlign.Justify

                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.dashboard_image),
                    contentDescription = "ballot icon",
                    Modifier.size(width = 167.dp, height = 180.dp)

                )
            }



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.58f)
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = TextFieldBackground),
                border = BorderStroke(width = 2.dp, color = TextOnBackgroundDark)
            ) {
                Card(Modifier.verticalScroll(rememberScrollState()) , colors = CardDefaults.elevatedCardColors(containerColor = Color.Transparent)) {
                    each_poll_item(modifier = Modifier.height(150.dp))
                    each_poll_item(modifier = Modifier.height(150.dp))
                    each_poll_item(modifier = Modifier.height(150.dp))
                    each_poll_item(modifier = Modifier.height(150.dp))

                    //implement here the list of the votes that are currently active

                    // fix the height of the each item to 150.dp

                }





            }

            Spacer(modifier = Modifier.size(15.dp))
            Column (modifier=Modifier.padding(10.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

                Card(modifier = Modifier
                    .clickable { }
                    .size(height = 50.dp, width = 250.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(pressedElevation = 20.dp, defaultElevation = 30.dp),
                    colors = CardDefaults.cardColors(containerColor = TextFieldBackground)) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Previous Poll created",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextOnBackgroundDark,
                            textAlign = TextAlign.Center
                        )
                    }

                }
                Spacer(modifier = Modifier.size(10.dp))

                Card(modifier = Modifier
                    .clickable { }
                    .size(height = 50.dp, width = 250.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(pressedElevation = 20.dp, defaultElevation = 30.dp),
                    colors = CardDefaults.cardColors(containerColor = TextFieldBackground)) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Participate in a poll",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextOnBackgroundDark,
                            textAlign = TextAlign.Center
                        )
                    }

                }
                Spacer(modifier = Modifier.size(10.dp))

                Row(
                    modifier=Modifier.height(50.dp) , horizontalArrangement = Arrangement.SpaceEvenly

                ) {
                    Card(modifier = Modifier
                        .clickable { }
                        .weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(pressedElevation = 20.dp, defaultElevation = 30.dp),
                        colors = CardDefaults.cardColors(containerColor = TextFieldBackground)) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Poll created",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextOnBackgroundDark,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                    Spacer(modifier = Modifier.size(10.dp))

                    Card(modifier = Modifier
                        .clickable { }
                        .weight(1f),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(pressedElevation = 20.dp, defaultElevation = 30.dp),
                        colors = CardDefaults.cardColors(containerColor = TextFieldBackground)) {
                        Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Create Poll",
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
                        .clickable { navController.navigate("profile") }


                )


            }

        }

    }

}