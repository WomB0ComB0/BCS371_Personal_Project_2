package com.example.bcs371_personal_project_2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bcs371_personal_project_2.LoginRegisterActivity
import com.example.bcs371_personal_project_2.ui.theme.BCS371_Personal_Project_2Theme

class ResultsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val score = intent.getIntExtra("SCORE", 0)
        setContent {
            BCS371_Personal_Project_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ResultsScreen(score = score, context = this)
                }
            }
        }
    }
}

@Composable
fun ResultsScreen(score: Int, context: Context) {
    val sharedPreferences = context.getSharedPreferences("QuizResults", Context.MODE_PRIVATE)
    val highScore = sharedPreferences.getInt("HighScore", 0)
    
    if (score > highScore) {
        sharedPreferences.edit().putInt("HighScore", score).apply()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Quiz Results",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your Score: $score",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "High Score: ${if (score > highScore) score else highScore}",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { context.startActivity(android.content.Intent(context, LoginRegisterActivity::class.java)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Main Menu")
        }
    }
}
