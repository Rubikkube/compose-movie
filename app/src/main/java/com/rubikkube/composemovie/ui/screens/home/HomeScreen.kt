@file:Suppress("PreviewAnnotationInFunctionWithParameters")
package com.rubikkube.composemovie.ui.screens.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rubikkube.composemovie.R
import com.rubikkube.composemovie.components.PlayingNowMoviesList
import com.rubikkube.composemovie.components.UpComingMoviesListItem
import com.rubikkube.composemovie.data.remote.Status
import com.rubikkube.composemovie.data.remote.responses.MovieModel
import com.rubikkube.composemovie.navigation.Actions
import com.rubikkube.composemovie.ui.theme.*
import com.rubikkube.composemovie.viewmodel.AppViewModel


@Preview(showBackground = true)
@Composable
fun HomeScreen(
    viewModel: AppViewModel,
    actions: Actions
) {
    viewModel.getPlayNowMovies("d6df0466d78f7e3c54d746506bb86334")
    val playMovies = arrayListOf<MovieModel>()

    val playNowResult = viewModel.playNowMovies.collectAsState().value
    when(playNowResult.status) {
        Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Status.SUCCESS -> {
            playNowResult.data?.results?.let {
                playMovies.addAll(it)
                viewModel.getUpcomingMovies("d6df0466d78f7e3c54d746506bb86334")
            }
        }
        Status.EMPTY -> {
            Text(
                modifier = Modifier.wrapContentSize(Alignment.Center),
                text = "No Play Now Movies Found..",
                color = MaterialTheme.colors.onBackground
            )
        }
        Status.ERROR -> {
            Text(
                modifier = Modifier.wrapContentSize(Alignment.Center),
                text = "${playNowResult.message}",
                color = MaterialTheme.colors.onBackground
            )
        }
    }

    val upcomingMovies = viewModel.upcomingMovies.collectAsState().value
    when(upcomingMovies.status) {
        Status.SUCCESS -> {
            upcomingMovies.data?.results?.let { upcomingMoviesData ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .absolutePadding(
                                top = 20.dp,
                                left = 20.dp,
                                right = 20.dp,
                            )
                    ) {
                        item {
                            GreetingSection(title = "What are you looking For?")
                            SearchSection()
                            PlayingNowMoviesList(playMovies, actions)
                        }

                        item {
                            Spacer(modifier = Modifier.padding(top = 40.dp))
                            Text(
                                text = "Upcoming Movies",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    fontFamily = FontFamily(Font(R.font.pt_sans_bold)),
                                    color = dark_gray
                                )
                            )
                        }

                        itemsIndexed(upcomingMoviesData) { index: Int, item: MovieModel ->
                            Spacer(modifier = Modifier.padding(top = 10.dp))
                            UpComingMoviesListItem(model = item, actions = actions)
                        }
                    }
                }
            }
        }
        Status.ERROR -> {
            Text(
                modifier = Modifier.wrapContentSize(Alignment.Center),
                text = "No Movies Found",
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun GreetingSection(title: String) {
    Row(
        Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.onBackground,
            style = TextStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = FontFamily(Font(R.font.pt_sans_bold))
            ),
            fontSize = 22.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            modifier = Modifier.clickable {
                val mode = AppCompatDelegate.getDefaultNightMode()
                if(mode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            },
            painter = painterResource(id = R.drawable.ic_dark),
            contentDescription = null,
            tint = if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                white
            } else {
                black
            },
        )
    }

}

@Composable
fun SearchSection() {
    Card(
        modifier = Modifier
            .absolutePadding(top = 20.dp)
            .fillMaxWidth()
            .height(50.dp),
        backgroundColor = MaterialTheme.colors.surface,

        ) {
        Row(
            modifier = Modifier
                .height(50.dp),
            Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .absolutePadding(
                        left = 15.dp
                    )
                ,
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
            )
            Text(
                text = "Search for Movies, event & more...",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .absolutePadding(
                        left = 10.dp
                    ),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pt_sans_regular)),
                    fontSize = 15.sp
                )
            )

            Button(
                onClick = {  },
                modifier = Modifier
                    .absolutePadding(
                        left = 15.dp
                    )
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null,
                    tint = white
                )
            }
        }
    }
}