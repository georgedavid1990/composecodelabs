package com.jadc.composecodelabs.ui.screens

import androidx.activity.compose.LocalActivity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jadc.composecodelabs.R
import com.jadc.composecodelabs.ui.theme.ComposeCodelabsTheme
import com.jadc.composecodelabs.ui.widgets.AlignYourBodyRow
import com.jadc.composecodelabs.ui.widgets.BottomNavigation
import com.jadc.composecodelabs.ui.widgets.FavoriteCollectionsGrid
import com.jadc.composecodelabs.ui.widgets.RailNavigation
import com.jadc.composecodelabs.ui.widgets.SearchBar

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun BasicLayoutsCodeLab() {
    val activity = LocalActivity.current
    activity?.let {
        val windowSizeClass = calculateWindowSizeClass(activity)
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Portrait()
            }
            WindowWidthSizeClass.Expanded -> {
                Landscape()
            }
        }
    }
}

@Composable
fun Portrait() {
    Scaffold(
        bottomBar = { BottomNavigation() }
    ) { padding ->
        HomeScreen(modifier = Modifier.padding(padding))
    }
}

@Composable
fun Landscape() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Row {
            RailNavigation()
            HomeScreen()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(16.dp))
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(title),
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        content()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    ComposeCodelabsTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}