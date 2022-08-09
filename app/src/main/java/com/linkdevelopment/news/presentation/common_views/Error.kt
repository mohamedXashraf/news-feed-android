package com.linkdevelopment.news.presentation.common_views

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Error(error: String)
{
    Toast.makeText(LocalContext.current, error, Toast.LENGTH_SHORT).show()
}