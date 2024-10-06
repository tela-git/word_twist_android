package com.example.wordtwist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wordtwist.ui.screens.DifficultyScreen
import com.example.wordtwist.ui.screens.GameScreen

@Composable
fun WordTwistApp(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
    ){ innerPadding->
        NavHost(
            navController = navHostController,
            startDestination = GameScreens.Difficulty.route
        )  {
            composable(
                route = GameScreens.Difficulty.route
            ) {
                DifficultyScreen(
                    modifier = modifier
                        .padding(innerPadding)
                )
            }
            composable(
                route = GameScreens.Game.route
            ) {
                GameScreen(
                    navController = navHostController,
                    modifier = modifier
                        .padding(innerPadding)
                )
            }
        }
    }
}

sealed class GameScreens(val route: String) {
    data object Difficulty: GameScreens("Difficulty")
    data object Game: GameScreens("Game")
}