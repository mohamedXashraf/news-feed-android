package com.linkdevelopment.news.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linkdevelopment.news.R
import com.linkdevelopment.news.presentation.common_views.RemoteImageView
import com.linkdevelopment.news.presentation.common_views.SmallRemoteImageView
import com.linkdevelopment.news.presentation.common_views.TextView
import com.linkdevelopment.news.ui.theme.PrimaryColor

@Composable
fun DrawerView(action: (String) -> Unit) {

    LazyColumn{
        item { DrawerHeader() }
        item { DrawerAction(painterResource(id = R.drawable.ic_explore), stringResource(id = R.string.explore), action) }
        item { DrawerAction(painterResource(id = R.drawable.ic_chat), stringResource(id = R.string.chat), action) }
        item { DrawerAction(painterResource(id = R.drawable.ic_gallery), stringResource(id = R.string.gallery), action) }
        item { DrawerAction(painterResource(id = R.drawable.ic_wishlist), stringResource(id = R.string.wishlist), action) }
        item { DrawerAction(painterResource(id = R.drawable.ic_learning), stringResource(id = R.string.learning), action) }
    }
}

@Composable
private fun DrawerHeader() {
    Row( modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(shape = CircleShape, modifier = Modifier.padding(16.dp)) {
            SmallRemoteImageView("https://t.ctcdn.com.br/WXGIAsXxAX1C2Qy2rqN4eoWyVCg=/400x400/smart/i490761.jpeg")
        }
        Column(modifier = Modifier.weight(1f)) {
            TextView(text = stringResource(id = R.string.welcome), size = 10, wrapped = true)
            TextView(text = stringResource(id = R.string.username), size = 16, color = Color.DarkGray, wrapped = true)
        }
        Icon(Icons.Rounded.KeyboardArrowRight, contentDescription = "", modifier = Modifier.size(50.dp), tint = Color.LightGray)
    }
}

@Composable
private fun DrawerAction(icon: Painter, label: String, action: (String) -> Unit) {
    TextButton(onClick = { action(label) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 24.dp)
        ) {
            Icon(icon, contentDescription = "", modifier = Modifier.size(24.dp), tint = Color.DarkGray)
            Spacer(modifier = Modifier.width(7.dp))
            TextView(text = label, size = 18, color = Color.DarkGray)
        }
    }
}