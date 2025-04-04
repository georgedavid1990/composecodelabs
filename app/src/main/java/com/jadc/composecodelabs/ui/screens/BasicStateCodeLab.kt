package com.jadc.composecodelabs.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jadc.composecodelabs.ui.viewmodels.BasicStateCodeLabViewModel
import com.jadc.composecodelabs.ui.widgets.state.WellnessTasksList

// State is. Events happen.
// Event - An event is generated by the user or another part of the program.
// Update State - An event handler changes the state that is used by the UI.
// Display State - The UI is updated to display the new state.

/*
The Composition: a description of the UI built by Jetpack Compose when it executes composables.
Initial composition: creation of a Composition by running composables the first time.
Recomposition: re-running composables to update the Composition when data changes.
 */

@Composable
fun BasicStateCodeLab(
    modifier: Modifier = Modifier,
    basicStateCodeLabViewModel: BasicStateCodeLabViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()
        WellnessTasksList(
            list = basicStateCodeLabViewModel.tasks,
            onCheckedTask = { task, checked ->
                basicStateCodeLabViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                basicStateCodeLabViewModel.remove(task)
            }
        )
    }
}

@Composable
fun WaterCounter(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, { count++ }, modifier)
}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onIncrement,
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text("Add one")
        }
    }
}