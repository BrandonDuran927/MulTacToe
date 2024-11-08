package com.brandon.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandon.tictactoe.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val selectedBox by remember { mutableStateOf<Box?>(null) }
                    PlayBoard(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Box(
    val x: Float,
    val y: Float,
    val label: String
)

@Composable
fun PlayBoard(
    modifier: Modifier = Modifier,
    selectedBox: Box
) {

    val measurer = rememberTextMeasurer()
    val myTextStyle = TextStyle.Default.copy(
        color = MaterialTheme.colorScheme.primary,
        fontSize = 22.sp
    )
    val boxSize = Size(width = 300f, height = 300f)
    val boxes by remember {  }


    Canvas(
        modifier = modifier
            .width(400.dp)
            .height(500.dp)
            .pointerInput(Unit) {
                detectTapGestures { change ->
                    println("change X: ${change.x} \n change Y: ${change.y}")
                    // TODO: Pass a function here that return a specific 'Box' and create a mutable selectedBox..
                }
            }
            .background(Color.Green.copy(alpha = 0.3f))
    ) {
        var row2X = 0
        var row3X = 0
        // 3, 6 -> move to next row
        for (i in 0..8) {
            var x = (boxSize.width * i / 0.9f) + 50f
            val color =
                if (i % 2 == 0) Color.Red.copy(alpha = 0.3f) else Color.Blue.copy(alpha = 0.3f)
            val y: Float = when (i) {
                in 3..5 -> {
                    x = (boxSize.width * row2X / 0.9f) + 50f
                    row2X += 1
                    boxSize.height + 20f * 2
                }
                in 6..8 -> {
                    x = (boxSize.width * row3X / 0.9f) + 50f
                    row3X += 1
                    boxSize.height * 2 + (20f * 3)
                }
                else -> {
                    20f
                }
            }
            drawRect(
                topLeft = Offset(
                    x = x,
                    y = y
                ),
                color = color,
                size = boxSize
            )
            //TODO: Add selectedBox
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev() {
    PlayBoard()
}