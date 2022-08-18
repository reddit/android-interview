package com.reddit.rickandmortyapp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

/**
 * Main Activity for Compose support
 * If you'd like to use Compose, please update the Android Manifest to reference this Activity
 */
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Rick and Morty App")
                        },
                        backgroundColor = Color(0xFF6200EE),
                        elevation = 12.dp
                    )
                }, content = {
                    Surface {
                        Feed(
                            posts = emptyList() /* TODO */
                        )
                    }
                })
        }
    }
}