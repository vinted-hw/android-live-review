package com.vinted.demovinted.features.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.vinted.demovinted.R
import com.vinted.demovinted.models.ItemBox

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FeedItemCard(
    item: ItemBox,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("feed_item"),
    ) {
        GlideImage(
            model = item.mainPhoto?.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop,
            failure = placeholder(R.drawable.placeholder_image),
        )
        Text(
            text = item.category,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = item.formattedPrice,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = item.brandTitle.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
        )
        if (item.size != null) {
            Text(
                text = item.size,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}
