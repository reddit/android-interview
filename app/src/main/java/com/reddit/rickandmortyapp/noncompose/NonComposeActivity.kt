package com.reddit.rickandmortyapp.noncompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reddit.rickandmortyapp.R

class NonComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_non_compose)
    }
}