package com.example.aroundegypt.ui.home.view.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    isSearchActive: Boolean,
    onSearchSubmitted: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding( top = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
        }
        Spacer(modifier = Modifier.width(8.dp))


        OutlinedTextField(
            value = searchText,
            onValueChange = {
                onSearchTextChanged(it)
            },
            modifier = Modifier
                .weight(1f)
                .height(55.dp),
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onSearchSubmitted()
                focusManager.clearFocus()
            }),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = Color.Gray,
                disabledBorderColor = Color.LightGray,
                disabledLeadingIconColor = Color.Gray
            )
        )



        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {  }) {
            Icon(imageVector = Icons.Filled.FilterList, contentDescription = "Filter")
        }
    }
}