package com.example.aroundegypt.ui.home.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aroundegypt.ui.home.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: HomeViewModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 54.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {  }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
        }

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = "search",
            onValueChange = {  },
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(horizontal = 2.dp),
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = { /*TODO: Implement Filter functionality*/ }) {
            Icon(imageVector = Icons.Filled.FilterList, contentDescription = "Filter")
        }
    }
}
