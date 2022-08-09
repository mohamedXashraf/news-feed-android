package com.linkdevelopment.news.presentation.common_views

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.platform.LocalContext

@Composable
fun Error(error: String) = message(LocalContext.current, error)

fun message(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()