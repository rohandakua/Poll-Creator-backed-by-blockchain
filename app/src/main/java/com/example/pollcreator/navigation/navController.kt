package com.example.pollcreator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pollcreator.screens.admin_dashboard
import com.example.pollcreator.screens.admin_registration
import com.example.pollcreator.screens.change_password_screen
import com.example.pollcreator.screens.userDashboard
import com.example.pollcreator.screens.login_page
import com.example.pollcreator.screens.login_register
import com.example.pollcreator.screens.profile
import com.example.pollcreator.screens.splash_screen
import com.example.pollcreator.screens.success_screen
import com.example.pollcreator.screens.two_centre_button
import com.example.pollcreator.viewModel.signInViewModel


@Composable
fun navController(modifier: Modifier = Modifier,viewModel: signInViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashScreen", builder = {

        composable("splashScreen"){
            splash_screen(navController=navController)
        }
        composable("userOrAdmin"){
            LaunchedEffect(true) {
                viewModel.logout()
            }
            two_centre_button(navController=navController)     // implement btn1Click and btn2Click
        }
        composable("login_register/{isAdmin}"){
            val isadmin = if(it.arguments?.getString("isAdmin").equals("true")) true else false
            login_register(
                navController=navController, isAdmin = isadmin
            )



        }
        composable("loginOrSignUp/{isadmin}/{islogin}"){
            val islogin: Boolean = if((it.arguments?.getString("islogin")).equals("true")) true else false
            val isadmin = if(it.arguments?.getString("isadmin").equals("true")) true else false
            login_page(navController=navController, isLoginFromSuper = islogin, isAdminFromSuper = isadmin)

        }

        composable(route = "userDashBoard"){
            userDashboard(navController=navController)
        }

        composable(route = "adminDashBoard"){
            admin_dashboard(navController=navController)
        }



        composable(route = "successAnimation/{isSuccess}"){

            success_screen(navController = navController )

        }

        composable(route="profile"){
            profile( navController = navController)
        }

        composable(route="changePassword"){
            change_password_screen(navController=navController)
        }

        composable(route="changeRole"){
            admin_registration(navController=navController)
        }





    })
    
}