package id.haaweejee.rawgandroid.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import id.haaweejee.rawgandroid.ui.theme.DarkBlueGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
         },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DarkBlueGrey
        ),
    )
}