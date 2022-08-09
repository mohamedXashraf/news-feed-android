package com.linkdevelopment.news.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.linkdevelopment.news.presentation.details.Details
import com.linkdevelopment.news.presentation.home.Home
import com.linkdevelopment.news.utils.Constants.HOME
import com.linkdevelopment.news.utils.Constants.DETAILS

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent { Application() }
    }
}

@Composable
fun Application()
{
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME, builder = {
        composable(HOME, content = { Home(navController) })
        composable("$DETAILS/{id}",
            arguments = listOf(navArgument("id", builder = {
                    type = NavType.StringType
            })),
            content = { Details(navController, it.arguments?.getString("id") ?: "") }
        )
    })
}