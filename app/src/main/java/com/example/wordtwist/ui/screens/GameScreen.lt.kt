package com.example.wordtwist.ui.screens

import android.graphics.drawable.shapes.Shape
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.VibrationEffect
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordtwist.R
import com.example.wordtwist.WordTwistApp


val tempWordList = listOf(
    "search","four", "rough", "rainbow", "mobile", "bottle", "tuition", "nest", "bird"
)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier
) {
    GamePage(
        modifier =modifier
            .fillMaxSize(),
        wordList = tempWordList
    )
}

@Composable
fun GamePage(
    modifier: Modifier = Modifier,
    wordList: List<String>
) {
    val context = LocalContext.current
    var currentWordIndex by remember { mutableStateOf(0) }
    var inputValue by remember { mutableStateOf("") }

    val soundPool = remember {
        SoundPool.Builder()
            .setContext(context)
            .setMaxStreams(1)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            .build()
    }

    val correctSound = remember { soundPool.load(context, R.raw.correct, 1) }
    val wrongSound = remember { soundPool.load(context, R.raw.wrong, 1) }

    DisposableEffect(Unit) {
        onDispose {
            soundPool.release()
        }
    }

    WordTwistChallenge(
        onSubmitClick = { guessWord ->
            if(guessWord == wordList[currentWordIndex]) {
                inputValue = ""
                currentWordIndex++
                soundPool.play(correctSound, 1f, 1f, 1, 0, 1f)
            } else {
                soundPool.play(wrongSound, 1f, 1f, 1, 0, 1f)
            }
        },
        word = wordList[currentWordIndex],
        inputValue = inputValue,
        onInputValueChange = { inputValue = it },
        modifier = modifier
    )
}

@Composable
fun WordTwistChallenge(
    modifier: Modifier = Modifier,
    word: String,
    onSubmitClick: (String) -> Unit,
    inputValue: String,
    onInputValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Wrod Twist",fontSize = 24.sp)

        Spacer(Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0..<(word.length)) {
                ElevatedCard(
                    shape = RoundedCornerShape(1),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier.size(40.dp, 40.dp)
                ) {
                    Text(
                        text = if(i >= inputValue.length) " " else if (inputValue.isNotEmpty()) {
                            inputValue[i].toString()
                        } else " "
                    )
                }
            }
        }
        Spacer(Modifier.height(80.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            word.forEach { letter ->
                ElevatedCard(
                    shape = RoundedCornerShape(10),
                    onClick = {
                        onInputValueChange(inputValue + letter.toString())
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = letter.toString(),
                        modifier = Modifier
                            .padding(8.dp),
                        fontSize = 36.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(80.dp))
        Button(
            onClick = {
                onSubmitClick(inputValue)
            }
        ) {
            Text(
                text = "Submit"
            )
        }
    }

}

fun shuffle(word: String): String {
    val set = mutableSetOf<Char>()
    while(set.size == word.length) {
        val randomWord = word.random()
        if(set.contains(randomWord).not()) {
            set.add(randomWord)
        }
    }
    return set.toString()
}