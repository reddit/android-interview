package com.reddit.rickandmortyapp.main

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.reddit.rickandmortyapp.compose.ComposeActivity
import com.reddit.rickandmortyapp.compose.composables.RickAndMortyAppBar
import com.reddit.rickandmortyapp.compose.theme.AppTheme
import com.reddit.rickandmortyapp.noncompose.NonComposeActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Column {
                    RickAndMortyAppBar()
                    ActivityOptions()
                }
            }
        }
    }
}

@Composable
fun ActivityOptions() {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(20.dp, 0.dp, 10.dp, 0.dp),
            onClick = {
                context.startActivity(
                    Intent(context, ComposeActivity::class.java)
                )
            }) {
            Text(text = "Compose")
        }

        Button(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(10.dp, 0.dp, 20.dp, 0.dp),
            onClick = {
                context.startActivity(
                    Intent(context, NonComposeActivity::class.java)
                )
            }) {
            Text(text = "Non Compose")
        }
    }
}