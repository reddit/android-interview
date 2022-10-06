package com.reddit.rickandmortyapp.compose.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun RickAndMortyAppBar() {
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
}