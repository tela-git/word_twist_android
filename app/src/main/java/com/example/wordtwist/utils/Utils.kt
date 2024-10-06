package com.example.wordtwist.utils
import kotlin.random.Random

fun shuffleWord(word: String): String {
    val charList = word.toMutableList()
    charList.shuffle(Random(1))
    return charList.joinToString("")
}
