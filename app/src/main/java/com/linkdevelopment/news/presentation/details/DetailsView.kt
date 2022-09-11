package com.linkdevelopment.news.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linkdevelopment.model.News
import com.linkdevelopment.news.R
import com.linkdevelopment.news.presentation.common_views.ButtonView
import com.linkdevelopment.news.presentation.common_views.RemoteImageView
import com.linkdevelopment.news.presentation.common_views.TextView
import com.linkdevelopment.news.ui.theme.PrimaryColor
import com.linkdevelopment.news.utils.DateFormatter
import org.koin.androidx.compose.getViewModel

@Composable
fun Details(navController: NavHostController, newsId: String, viewModel: DetailsViewModel = getViewModel()) {
    val context = LocalContext.current
    val news = viewModel.news.observeAsState(initial = null)

    if (news.value == null) {
        viewModel.getNewsById(newsId)
    } else {
        DetailsView(news.value, { navController.popBackStack() }, {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(news.value?.url)))
        })
    }
}

@Composable
private fun DetailsView(news: News? = null, backAction: () -> Unit = {}, urlAction: () -> Unit = {})
{
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White,
        topBar = { TopBar(backAction) }
    ) {
        LazyColumn {
            item {
                Box(contentAlignment = Alignment.BottomEnd) {
                    if (!news?.urlToImage.isNullOrEmpty()) {
                        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(10.dp)) {
                            RemoteImageView(news?.urlToImage!!)
                        }
                    }
                    TextView(text = DateFormatter.format(news?.publishedAt ?: ""), color = Color.White, horizontalPadding = 16, verticalPadding = 16, size = 13, align = TextAlign.End)
                }
            }
            item {
                TextView(text = news?.title ?: "", horizontalPadding = 16, verticalPadding = 5, color = Color.DarkGray, weight = FontWeight.SemiBold)
                TextView(text = stringResource(id = R.string.author, news?.author ?: ""), horizontalPadding = 16, weight = FontWeight.Normal, size = 13)
                Spacer(modifier = Modifier.height(20.dp))
                TextView(text = news?.description ?: "", horizontalPadding = 16, verticalPadding = 10, size = 14, color = Color.DarkGray, weight = FontWeight.Normal)
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                ButtonView(label = stringResource(id = R.string.open_website), urlAction)
            }
        }
    }
}

@Composable
private fun TopBar(backAction: () -> Unit)
{
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = backAction) {
                Icon(Icons.Filled.ArrowBack, "", tint = Color.White)
            }
        },
        title = { TextView(text = stringResource(id = R.string.title), color = Color.White) },
        elevation = 5.dp,
        backgroundColor = PrimaryColor,
    )
}

@Preview(showBackground = true)
@Composable
fun DetailsViewPreview()
{
    DetailsView()
}