package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.aroundegypt.R
import androidx.navigation.NavController
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel

@Composable
fun ExperienceCard(
    title: String,
    image: String,
    views: Int,
    likes: Int,
    modifier: Modifier = Modifier,
    recommended: Boolean = false,
    isMostRecent: Boolean = false,
    id: String ,
    navController: NavController? = null,
    viewModel: HomeViewModel? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (navController != null && id.isNotEmpty()) {
                    navController.navigate("experience/$id")
                }
            },
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xF3FFFFFF))
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                placeholder = rememberAsyncImagePainter(model = R.drawable.pyramids),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isMostRecent) 154.dp else 154.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
            )
            if (recommended) {
                Box(
                    modifier = Modifier
                        .padding(10.dp), contentAlignment = Alignment.TopStart
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(6.dp))
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "RECOMMENDED",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = rememberAsyncImagePainter(model = R.drawable.ic_eye),
                        contentDescription = "View Icon",
                        modifier = Modifier.size(12.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = views.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_info_icon),
                    contentDescription = "Info Icon",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_eye),
                    contentDescription = "360 Icon",
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    if(viewModel != null && id.isNotEmpty()){
                        viewModel.likeAnExperience(id)

                    }

                }
            ) {
                Text(text = likes.toString(), style = MaterialTheme.typography.bodyMedium)
                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_add_favorite),
                    contentDescription = "Heart Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Red
                )
            }
        }
    }
}