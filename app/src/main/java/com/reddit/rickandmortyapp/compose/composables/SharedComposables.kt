package com.reddit.rickandmortyapp.compose.composables

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RickAndMortyAppBar() {
    TopAppBar(
        title = {
            Text(
                color = MaterialTheme.colorScheme.onPrimary,
                text = "Rick and Morty App",
                modifier = Modifier.statusBarsPadding(),
            )
        },
        backgroundColor = MaterialTheme.colorScheme.primary,
        elevation = 12.dp,
        modifier = Modifier.height(56.dp + WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
    )
}