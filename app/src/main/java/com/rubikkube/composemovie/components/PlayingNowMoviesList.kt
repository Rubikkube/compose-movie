package com.rubikkube.composemovie.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rubikkube.composemovie.R
import com.rubikkube.composemovie.data.remote.responses.MovieModel
import com.rubikkube.composemovie.navigation.Actions
import com.rubikkube.composemovie.ui.theme.dark_gray

@Composable
fun PlayingNowMoviesList(
    moviesList: List<MovieModel>,
    action: Actions
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp
            )
    ) {
        Text(
            text = "Playing Now",
            style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily(Font(R.font.pt_sans_bold)),
                color = dark_gray
            )
        )
        LazyRow(
            modifier = Modifier.padding(top = 15.dp)
        ) {
            itemsIndexed(moviesList) { index: Int, item: MovieModel ->
                PlayingNowMovieListItem(model = item, action = action)
            }
        }
    }
}