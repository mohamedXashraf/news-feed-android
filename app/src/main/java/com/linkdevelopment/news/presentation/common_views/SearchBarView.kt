package com.linkdevelopment.news.presentation.common_views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.linkdevelopment.news.R
import com.linkdevelopment.news.ui.theme.PrimaryColor

@Composable
fun SearchBarView(textWatcher: (String) -> Unit, closeAction: () -> Unit) {

    val input = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
    ) {
        OutlinedTextField(modifier = Modifier.weight(1f), value = input.value,
            placeholder = { Text(text = stringResource(id = R.string.search)) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = PrimaryColor
            ),
            onValueChange = {
                input.value = it
                textWatcher(it)
            }
        )
        IconButton(onClick = closeAction) {
            Icon(Icons.Rounded.Close, "", tint = Color.LightGray)
        }
    }
}