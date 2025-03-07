package com.jadc.composecodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jadc.composecodelabs.ui.screens.BasicCodeLab
import com.jadc.composecodelabs.ui.screens.BasicLayoutsCodeLab
import com.jadc.composecodelabs.ui.screens.BasicStateCodeLab
import com.jadc.composecodelabs.ui.theme.ComposeCodelabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCodelabsTheme {
               MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    Surface (modifier = modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Menu()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Compose Codelabs!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun Menu() {
    var content: (@Composable () -> Unit)? by remember { mutableStateOf(null) }

    BackHandler { content = null }

    if (content != null) {
        content?.invoke()
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    content = { BasicCodeLab() }
                }
            ) {
                Text("Basics")
            }

            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    content = { BasicLayoutsCodeLab() }
                }
            ) {
                Text("Layouts")
            }

            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {
                    content = { BasicStateCodeLab() }
                }
            ) {
                Text("States")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeCodelabsTheme {
        OnboardingScreen(
            onContinueClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ComposeCodelabsTheme {
        MyApp(Modifier.fillMaxSize())
    }
}