package com.linkdevelopment.news.presentation.common_views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextView(text: String, size: Int = 24, color: Color = Color.LightGray, horizontalPadding: Int = 0, verticalPadding: Int = 0, weight: FontWeight = FontWeight.Normal, align: TextAlign = TextAlign.Start, wrapped: Boolean = false)
{
    Text(text = text,
        modifier = Modifier.padding(horizontal = horizontalPadding.dp, vertical = verticalPadding.dp).fillMaxWidth(),
        textAlign = align,
        style = TextStyle(
        color = color,
        fontSize = size.sp,
        fontWeight = weight
    ))
}