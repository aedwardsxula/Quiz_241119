package com.mad.quiz_241107

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.max

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clicks = remember { mutableStateOf("") }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text("Quiz Nov 19th", style = MaterialTheme.typography.displayMedium)

                Row {
                    Button(onClick = {
                        if (! clicks.value.contains('1')) {
                            clicks.value += "1"
                        }
                    }) {
                        Text("Button 1")
                    }
                    Spacer(Modifier.width(32.dp))
                    Button(onClick = {
                        if (! clicks.value.contains('2')) {
                            clicks.value += "2"
                        }
                    }) {
                        Text("Button 2")
                    }
                }
                if (clicks.value.length == 2) {
                    Text("Hooray, all the buttons were clicked.")
                }
                DropDownDemo()
            }
        }
    }
}

@Composable
fun DropDownDemo() {

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableIntStateOf(0)
    }

    val pages = listOf("Home", "Page 2", "Page 3")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }
            ) {
                Text(text = pages[itemPosition.intValue])
                Icon(
                    imageVector = Icons.Rounded.ArrowDropDown,
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                pages.forEachIndexed { index, username ->
                    DropdownMenuItem(text = {
                        Text(text = username)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.intValue = index
                            Log.d("XU", "You selected menu item '${pages[index]}'")
                        })
                }
            }
        }
    }
}

fun generateRandoms(numValues: Int, minElement: Int = 70, maxElement: Int = 100): List<Int> {
    val nums = mutableSetOf<Int>()
    assert(numValues <= maxElement - minElement + 1) { "Don't have enough values." }
    while (nums.size != numValues) {
        nums.add((minElement..maxElement).random())
    }
    return nums.toList()
}

fun maxConsecutivePassingScores(scores: List<Int>) : Int {
    val validScores = scores.filter { score -> score in 70..100 }

    var answer = 1
    var current = answer

    for (i in 0 until scores.size - 1) {
        if (scores[i] < scores[i + 1]) {
            current++
        } else {
            answer = max(answer, current)
            current = 1
        }
    }
    return max(answer, current)
}


