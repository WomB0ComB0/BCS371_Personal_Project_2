package com.example.bcs371_personal_project_2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bcs371_personal_project_2.ui.theme.BCS371_Personal_Project_2Theme

class GameRulesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BCS371_Personal_Project_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameRulesScreen(
                        onStartQuizClick = {
                            startActivity(Intent(this, QuizActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun GameRulesScreen(onStartQuizClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Game Rules",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "1. The quiz consists of 5 questions.\n" +
                    "2. Some questions have multiple choice answers.\n" +
                    "3. Some questions have multiple correct answers.\n" +
                    "4. You can only submit an answer once.\n" +
                    "5. Your score will be displayed at the end of the quiz."
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onStartQuizClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Quiz")
        }
    }
}
