package com.example.aroundegypt.ui.experience.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aroundegypt.R
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.ui.theme.favoriteColor


@Composable
fun DetailsSection(state: SingleExperienceResponse) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = state.experience?.title ?:"",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.experience?.address ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Filled.Upload,
                        contentDescription = "Share",
                        tint = favoriteColor
                    )
                }
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "Like",
                        tint = favoriteColor
                    )
                }

                Text(
                    text = state.experience?.likesNo.toString() ?:"",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.Description),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = state.experience?.detailedDescription ?:"",
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )
    }
}