package com.example.pollcreator.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.R
import com.example.pollcreator.allSingeltonObjects
import com.example.pollcreator.dataclass.Poll
import com.example.pollcreator.onlineStorage.web3jDataModel
import com.example.pollcreator.ui.theme.ButtonBackground
import com.example.pollcreator.ui.theme.CardBackgroundLight
import com.example.pollcreator.ui.theme.CardBorderDark
import com.example.pollcreator.ui.theme.MainBackground
import com.example.pollcreator.ui.theme.TextFieldBackground
import com.example.pollcreator.ui.theme.TextOnBackgroundDark
import com.example.pollcreator.ui.theme.TextOnBackgroundLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
public fun userDashboard(
    modifier: Modifier = Modifier,
    onPrevVoteButton: () -> Unit = {},
    onProfileButton: () -> Unit = {},
    navController : NavController = rememberNavController()
) {

    val context = LocalContext.current
    var showWebView = allSingeltonObjects.privateKeyViewModelObject.showHelp.value
    var list:MutableList<Poll> = mutableListOf()
    LaunchedEffect(true){
        list= allSingeltonObjects.profileViewModel.getUpcomingPolls()
    }



    if (allSingeltonObjects.privateKeyViewModelObject.showDialog.value) {
        AlertDialog(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.5f), onDismissRequest = {
            if (allSingeltonObjects.privateKeyViewModelObject.privateKey.value == "") {
                Toast.makeText(
                    context,
                    "Please enter your private key to continue",
                    Toast.LENGTH_SHORT
                )
            } else {
                allSingeltonObjects.privateKeyViewModelObject.setShowDialog(false)
            }
        }, confirmButton = {
            Button(
                onClick = {

                    if (allSingeltonObjects.helperFunctions.isValidPrivateKey(
                            allSingeltonObjects.privateKeyViewModelObject.privateKey.value
                        )
                    ) {
                        allSingeltonObjects.privateKeyViewModelObject.setShowDialog(false)
                        Log.d("private key","${allSingeltonObjects.privateKeyViewModelObject.privateKey.value}")
                        CoroutineScope(Dispatchers.IO).launch {
                            delay(1000)
                            allSingeltonObjects.web3jDataModel= web3jDataModel()
                        }
                        Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Enter a valid private key", Toast.LENGTH_SHORT).show()
                        allSingeltonObjects.privateKeyViewModelObject.setPrivateKey("")
                    }


                },
                colors = ButtonColors(
                    containerColor = ButtonBackground,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Yellow,
                    disabledContentColor = Color.Red
                ),
                modifier = Modifier.size(120.dp, 50.dp)
            ) {
                Text(
                    text = "Submit",
                    fontSize = 20.sp
                )

            }

        }, containerColor = CardBackgroundLight,
            title = {
                Text(
                    text = "Enter your private key",
                    color = TextOnBackgroundDark,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Please enter your private key to continue. Worry not, your private key is stored on your phone locally. It is safe.",
                        color = TextOnBackgroundLight,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Justify
                    )
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = TextFieldBackground,
                            focusedTextColor = TextOnBackgroundDark,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedTextColor = TextOnBackgroundDark
                        ),
                        value = allSingeltonObjects.privateKeyViewModelObject.privateKey.value ?: "",
                        onValueChange =
                        { allSingeltonObjects.privateKeyViewModelObject.setPrivateKey(it) },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400)

                    )
                    Button(onClick = { allSingeltonObjects.privateKeyViewModelObject.setShowHelp(true)},
                        colors = ButtonColors(
                            containerColor = ButtonBackground,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Yellow,
                            disabledContentColor = Color.Red
                        )) {
                        Text(text = "How to get private key")
                    }

                    if (showWebView) {
                        YoutubeAppOpener(youtubeLink = allSingeltonObjects.ytShowPrivateKeyUrl)
                        allSingeltonObjects.privateKeyViewModelObject.setShowHelp(false)
                    }

                }
            }
        )
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
                text = "Go For Vote",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp,
                color = TextOnBackgroundDark

            )
            Text(
                text = "Keep calm & vote wisely",
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
                if(list.isEmpty()){
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
                    LazyColumn {
                        items(list){pollItem ->
                            each_poll_item_upcoming_poll(modifier = Modifier.height(150.dp),pollItem = pollItem)
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
                        .clickable {

                            navController.navigate("profile")
                        }

                )


            }

        }

    }

}