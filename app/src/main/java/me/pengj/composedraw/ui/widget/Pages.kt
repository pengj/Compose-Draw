package me.pengj.composedraw

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import me.pengj.composedraw.tutorials.*
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun Pages(page: PagerState, modifier: Modifier) {
    HorizontalPager(
        count = 7,
        state = page,
        modifier = modifier
    ) { pageIndex ->
        Surface(
            Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(pageIndex).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
        ){
            when(pageIndex) {
                0 -> TileLinesPage()
                1 -> JoyDivisionPage()
                2 -> CubicDisarrayPage()
                3 -> TriangularMeshPage()
                4 -> UnDeuxTroisPage()
                5 -> CirclePackingPage()
                6 -> HypnoticSquaresPage()
            }
        }
    }


}