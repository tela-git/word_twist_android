package com.example.wordtwist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.versionedparcelable.ParcelImpl
import com.example.wordtwist.R

@Composable
fun DifficultyScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.word_twist_bg),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(Modifier.height(80.dp))
        DifficultyButtons(

        )
    }

}

@Composable
fun DifficultyButtons(
) {
    val easyColorStops = arrayOf(
        Pair(0.0f, Color(0xFF2EFF00)),
        Pair(0.5f, Color(0xFF17B03B)),
        Pair(1.0f,Color(0xFF006176))
    )
    val mediumColorStops = arrayOf(
        Pair(0.0f, Color(0xFF01B1F8)),
        Pair(1.0f, Color(0xFF013FE6))
    )
    val hardColorStops = arrayOf(
        Pair(0.0f, Color(0xFFFF727B)),
        Pair(1.0f,Color(0xFFA60E70))
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DifficultyButton(
            onButtonClick = { },
            difficultyLevel = "Easy",
            colorStops = easyColorStops
        )
        DifficultyButton(
            onButtonClick = { },
            difficultyLevel = "Medium",
            colorStops = mediumColorStops
        )
        DifficultyButton(
            onButtonClick = { },
            difficultyLevel = "Hard",
            colorStops = hardColorStops
        )
    }
}

@Composable
fun DifficultyButton(
    onButtonClick: () -> Unit,
    difficultyLevel: String,
    colorStops: Array<Pair<Float,Color>>
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(
                Brush.horizontalGradient(
                    colorStops = colorStops
                )
            )
    ){
        Button(
            onClick = { onButtonClick() },
            modifier = Modifier
                .fillMaxWidth(0.6f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
        ) {
            Text(
                text = difficultyLevel
            )
        }
    }

}


@Preview
@Composable
fun DifficultyScreenPreview() {
    DifficultyScreen(

    )
}