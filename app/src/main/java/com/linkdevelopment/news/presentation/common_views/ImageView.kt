package com.linkdevelopment.news.presentation.common_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun ImageView(painter: Painter)
{
    Image(painter = painter, contentDescription = "", modifier = Modifier.padding(16.dp).size(50.dp))
}

@Composable
fun RemoteImageView(image: String)
{
    Image(
        painter = rememberCoilPainter( image),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(40.dp))
}