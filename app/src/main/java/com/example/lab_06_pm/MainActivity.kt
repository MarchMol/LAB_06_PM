package com.example.lab_06_pm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab_06_pm.ui.theme.LAB_06_PMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LAB_06_PMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(listOf("hola","como","estas"))
                }
            }
        }
    }
}

@Composable
fun Greeting(items: List<String>) {
    Column {
        items.forEach { text ->
            var isSelected by remember { mutableStateOf(false) }
            Text(
                text = text,
                modifier = Modifier
                    .clickable {
                        isSelected = !isSelected
                        // Add your onClick logic here
                    }
                    .padding(16.dp)
                    .background(if (isSelected) Color.Gray else Color.Transparent)
                    .fillMaxWidth(),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LAB_06_PMTheme {
        Greeting(listOf("hola","como","estas"))
    }
}