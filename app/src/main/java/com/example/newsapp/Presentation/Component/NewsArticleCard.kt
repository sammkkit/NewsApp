package com.example.newsapp.Presentation.Component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.newsapp.Domain.Model.Article
import com.example.newsapp.util.dateFormatter

@Composable
fun NewsArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit
) {
    val date = dateFormatter(article.publishedAt)
    Card(
        modifier = modifier.clickable {
            onClick(article)
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            ImageHolder(
                imageurl = article.urlToImage,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row (
                modifier = Modifier.fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = date ?: "",
                    style = MaterialTheme.typography.bodySmall
                )


            }

        }

    }

}