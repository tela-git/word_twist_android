package com.example.wordtwist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wordtwist.ui.screens.GameScreen

@Composable
fun WordTwistApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
    ){innerPadding->
        GameScreen(
            modifier = modifier
                .padding(innerPadding)
        )
    }
}