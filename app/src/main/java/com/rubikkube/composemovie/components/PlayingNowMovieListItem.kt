@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.rubikkube.composemovie.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.rubikkube.composemovie.R
import com.rubikkube.composemovie.data.remote.responses.MovieModel
import com.rubikkube.composemovie.navigation.Actions
import com.rubikkube.composemovie.ui.theme.dark_gray
import com.rubikkube.composemovie.ui.theme.red
import com.rubikkube.composemovie.utils.AppConstants

@Preview(showBackground = true)
@Composable
fun PlayingNowMovieListItem(
    model: MovieModel,
    action: Actions
) {
    Column(
        modifier = Modifier.padding(end = 10.dp)
            .width(130.dp)
            .clickable { action.actionDetails.invoke(model.id) }
    ) {
        Image(
            painter = rememberImagePainter(AppConstants.IMAGE_BASEURL+model.poster_path),
            contentDescription = null,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Text(
            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
            text = model.original_title,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pt_sans_regular)),
                fontSize = 18.sp,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = null,
                tint = red,
                modifier = Modifier.padding(top = 1.dp, start = 5.dp)
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = "${model.vote_average}/10",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = dark_gray,
                    fontFamily = FontFamily(Font(R.font.pt_sans_regular))
                )
            )
        }
    }
}