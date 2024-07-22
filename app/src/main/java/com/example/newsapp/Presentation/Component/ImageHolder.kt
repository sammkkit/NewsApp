package com.example.newsapp.Presentation.Component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R

@Composable
fun ImageHolder(
    imageurl: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model =ImageRequest
            .Builder(LocalContext.current)
            .data(imageurl)
            .crossfade(true)
            .build()
        ,
        contentDescription ="Image of news",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
        , placeholder = painterResource(id = R.drawable.placeholder_loading),
        error = painterResource(id = R.drawable.no_image)
    )

}