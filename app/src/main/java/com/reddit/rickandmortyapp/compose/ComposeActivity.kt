package com.reddit.rickandmortyapp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import com.reddit.rickandmortyapp.compose.composables.RickAndMortyAppBar
import com.reddit.rickandmortyapp.compose.theme.AppTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                RickAndMortyAppBar()
                Surface {
                    Feed(
                        posts = emptyList() /* TODO */
                    )
                }
            }
        }
    }
}