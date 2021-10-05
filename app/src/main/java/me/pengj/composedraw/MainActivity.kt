package me.pengj.composedraw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import me.pengj.composedraw.ui.theme.ComposeDrawTheme

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDrawTheme {
                MainPage()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainPage() {
    Column(Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState()
        Pages(
            pagerState, Modifier
                .weight(1f)
                .fillMaxWidth()
        )
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = Color.DarkGray,
            inactiveColor = Color.LightGray
        )
    }

}