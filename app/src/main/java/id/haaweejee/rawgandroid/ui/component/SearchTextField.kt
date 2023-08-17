package id.haaweejee.rawgandroid.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    queryValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = queryValue,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "search",
            )
        },
        enabled = true,
        placeholder = { Text(text = "Search") },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
        ),
        textStyle = TextStyle(
            color = Color.Black,
        ),
    )
}