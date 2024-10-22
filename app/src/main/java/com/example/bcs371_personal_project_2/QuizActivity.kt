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

class QuizActivity : ComponentActivity() {
    private val questions = listOf(
        Question("What is the capital of France?", listOf("London", "Berlin", "Paris", "Madrid"), listOf(2)),
        Question("Which of these are primary colors?", listOf("Red", "Green", "Blue", "Yellow"), listOf(0, 2, 3)),
        Question("What is the largest planet in our solar system?", listOf("Earth", "Jupiter", "Mars", "Saturn"), listOf(1)),
        Question("Who wrote 'Romeo and Juliet'?", listOf("Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"), listOf(1)),
        Question("What is the chemical symbol for water?", listOf("H2O", "O2", "CO2", "NaCl"), listOf(0)),
        Question("Which country is known as the Land of the Rising Sun?", listOf("China", "Japan", "Thailand", "India"), listOf(1)),
        Question("What is the smallest prime number?", listOf("0", "1", "2", "3"), listOf(2)),
        Question("What is the square root of 64?", listOf("6", "7", "8", "9"), listOf(2)),
        Question("Who painted the Mona Lisa?", listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"), listOf(2)),
        Question("What is the capital of Australia?", listOf("Sydney", "Melbourne", "Canberra", "Brisbane"), listOf(2)),
        Question("Which planet is known as the Red Planet?", listOf("Earth", "Mars", "Jupiter", "Venus"), listOf(1)),
        Question("What is the largest ocean on Earth?", listOf("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"), listOf(3)),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BCS371_Personal_Project_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuizScreen(
                        questions = questions,
                        onQuizComplete = { score ->
                            val intent = Intent(this, ResultsActivity::class.java)
                            intent.putExtra("SCORE", score)
                            startActivity(intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun QuizScreen(questions: List<Question>, onQuizComplete: (Int) -> Unit) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedAnswers by remember { mutableStateOf(setOf<Int>()) }
    var showConfirmation by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            Text(
                text = question.text,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            question.options.forEachIndexed { index, option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = selectedAnswers.contains(index),
                        onCheckedChange = { isChecked ->
                            selectedAnswers = if (isChecked) {
                                selectedAnswers + index
                            } else {
                                selectedAnswers - index
                            }
                        }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { showConfirmation = true },
                enabled = selectedAnswers.isNotEmpty()
            ) {
                Text("Submit Answer")
            }

            if (showConfirmation) {
                AlertDialog(
                    onDismissRequest = { showConfirmation = false },
                    title = { Text("Confirm Answer") },
                    text = { Text("Are you sure you want to submit this answer?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                if (selectedAnswers == question.correctAnswers.toSet()) {
                                    score++
                                }
                                currentQuestionIndex++
                                selectedAnswers = emptySet()
                                showConfirmation = false
                            }
                        ) {
                            Text("Yes")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { showConfirmation = false }) {
                            Text("No")
                        }
                    }
                )
            }
        } else {
            onQuizComplete(score)
        }
    }
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswers: List<Int>
)
