package com.linkdevelopment.news.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linkdevelopment.model.News
import com.linkdevelopment.news.presentation.common_views.*
import com.linkdevelopment.news.R
import com.linkdevelopment.news.ui.theme.PrimaryColor
import com.linkdevelopment.news.utils.Constants.DETAILS
import com.linkdevelopment.news.utils.DateFormatter
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun Home(navController: NavHostController, viewModel: HomeViewModel = getViewModel()) {
    val state = viewModel.state.observeAsState(initial = HomeViewState.ViewInitializationState)

    HomeView()

    when (val viewState = state.value)
    {
        is HomeViewState.ViewInitializationState -> viewModel.intent.value = HomeViewIntent.GetAllArticles
        is HomeViewState.DataState -> HomeView(viewState.news) { navController.navigate("$DETAILS/${it.id}") }
        is HomeViewState.ErrorState -> Error(error = viewState.error.message ?: stringResource(id = R.string.error))
        is HomeViewState.LoadingState -> Loading()
    }
}

@Composable
private fun HomeView(news: MutableList<News> = mutableListOf(), selectionAction: (News) -> Unit = {})
{
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = { TopBar {
            scope.launch { scaffoldState.drawerState.open() }
        }},
        drawerBackgroundColor = Color.White,
        drawerContent = {
            DrawerView {
                scope.launch { scaffoldState.drawerState.close() }
                message(context = context, message = it)
            }
        }
    ) {
        if (news.isNotEmpty()) {
            LazyColumn {
                items(news) {
                    NewsItemView(newsItem = it, selectionAction)
                }
            }
        } else EmptyView()
    }
}

@Composable
private fun TopBar(navigationAction: () -> Unit)
{
    TopAppBar(
        title = { TextView(text = stringResource(id = R.string.title), color = Color.White) },
        elevation = 5.dp,
        backgroundColor = PrimaryColor,
        navigationIcon = {
            IconButton(onClick = navigationAction) {
                Icon(Icons.Filled.Menu, tint = Color.White, contentDescription = "")
            }
        },
    )
}

@Composable
private fun NewsItemView(newsItem: News, action: (News) -> Unit)
{
    Card(
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .clickable { action(newsItem) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            if (!newsItem.urlToImage.isNullOrEmpty()) RemoteImageView(newsItem.urlToImage!!)
            TextView(text = newsItem.title ?: "", horizontalPadding = 16, verticalPadding = 5, color = Color.DarkGray, weight = FontWeight.SemiBold, size = 16)
            TextView(text = stringResource(id = R.string.author, newsItem.author ?: ""), horizontalPadding = 16, weight = FontWeight.Normal, size = 13)
            TextView(text = DateFormatter.format(newsItem.publishedAt ?: ""), horizontalPadding = 16, verticalPadding = 5, size = 13, align = TextAlign.End)
            Box(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun EmptyView() {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageView(painter = painterResource(id = R.drawable.ic_logo))
        TextView(text = stringResource(id = R.string.empty_view), color = PrimaryColor, align = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview()
{
    HomeView()
}