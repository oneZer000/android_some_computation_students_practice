package com.example.some_computation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.some_computation.R.drawable.ic_launcher_background
import com.example.some_computation.ui.theme.SomeComputationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SomeComputationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ComputeScreen(Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun ComputeScreen(padding: Modifier) {
    var inputA by remember { mutableStateOf("") }
    var inputB by remember { mutableStateOf("") }
    var inputX by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = ic_launcher_background),
            contentDescription = "Clown",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputA,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) inputA = newValue
            },
            label = { Text("A=") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = inputB,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) inputB = newValue
            },
            label = { Text("B=") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = inputX,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) inputX = newValue
            },
            label = { Text("X=") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val a = inputA.toIntOrNull()
                val b = inputB.toIntOrNull()
                val x = inputX.toIntOrNull()
                result = if (a != null && b != null && x != null) {
                    computeY(a, b, x)
                } else null
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ВЫЧИСЛИТЬ")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Введите A, B и X, затем нажмите кнопку ВЫЧИСЛИТЬ",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodySmall
        )

        result?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Результат: ${"%.2f".format(it)}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview
@Composable
fun ComputeScreenPreview() {
    ComputeScreen(Modifier.padding(20.dp))
}

// Логика вычисления функции y(x)
fun computeY(a: Int, b: Int, x: Int): Double? {
    return if (x >= 3) {
        if (a == 0) null // деление на 0
        else x * (a * a + b * b).toDouble() / (6 * a)
    } else {
        x * (1 - a * b).toDouble()
    }
}
