package com.linkdevelopment.news.presentation.common_views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.linkdevelopment.news.ui.theme.PrimaryColor

@Composable
fun ButtonView(label: String, action: () -> Unit)
{
    Button(modifier = Modifier.padding(horizontal = 14.dp).height(50.dp).fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor),
        onClick = action) {
        Text(text = label, color = Color.White)
    }
}