package com.booknara.android.basicstatecodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun WaterCount(modifier: Modifier = Modifier) {
    StatefulCounter(modifier)
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(all = 16.dp)) {
        if (count > 0) {
            Text(
                text = "You've had $count glasses.",
                modifier = modifier.padding(all = 16.dp)
            )
        }

        Button(onClick = onIncrement, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text(text = "Add one")
        }
    }    
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by remember {
        mutableStateOf(0)
    }
    StatelessCounter(count = count, onIncrement = { count++ })
}
