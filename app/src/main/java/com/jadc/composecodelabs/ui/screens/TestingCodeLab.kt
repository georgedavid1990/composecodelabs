package com.jadc.composecodelabs.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jadc.composecodelabs.R

/*
Infinite animations are a special case that Compose tests understand so they're not going to keep the test busy.
*/

@Preview(backgroundColor = 0xFFC94242, showBackground = true, showSystemUi = true)
@Composable
fun TestingCodeLab() {
    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        var currentTargetPadding by remember {  mutableStateOf(1.dp) }
        LaunchedEffect(Unit) {
            // Start the animation
            currentTargetPadding = 8.dp
        }
        val animatedPadding = animateDpAsState(
            targetValue = currentTargetPadding,
            animationSpec = tween(durationMillis = 500),
            finishedListener = {
                currentTargetPadding = if (currentTargetPadding > 4.dp) {
                    1.dp
                } else {
                    8.dp
                }
            }
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(animatedPadding.value),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Testing CodeLab"
                )
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
            }
        }
    }
}