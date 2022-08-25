package com.reddit.rickandmortyapp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import com.reddit.rickandmortyapp.compose.theme.AppTheme

/**
 * Main Activity for Compose support
 * If you'd like to use Compose, please update the Android Manifest to reference this Activity
 */
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                TopAppBar(
                    title = {
                        Text(
                            color = MaterialTheme.colorScheme.onPrimary,
                            text = "Rick and Morty App"
                        )
                    },
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    elevation = 12.dp
                )
                Surface {
                    Feed(
                        posts = emptyList() /* TODO */
                    )
                }
            }
        }
    }
}