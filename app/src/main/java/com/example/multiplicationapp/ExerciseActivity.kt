package com.example.multiplicationapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ExerciseActivity : AppCompatActivity() {

    private var currentQuestion = 0
    private var correctAnswers = 0
    private var totalQuestions = 20
    private var number: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise_activity)

        val mode = intent.getStringExtra("mode")
        number = intent.getIntExtra("number", -1)

        val textViewQuestion = findViewById<TextView>(R.id.textViewQuestion)
        val editTextAnswer = findViewById<EditText>(R.id.editTextAnswer)
        val buttonSubmitAnswer = findViewById<Button>(R.id.buttonSubmitAnswer)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        generateQuestion(textViewQuestion)

        buttonSubmitAnswer.setOnClickListener {
            val answer = editTextAnswer.text.toString().toIntOrNull()
            if (answer != null) {
                val correctAnswer = calculateCorrectAnswer()
                if (answer == correctAnswer) {
                    correctAnswers++
                    textViewResult.text = "Правильный ответ"
                } else {
                    textViewResult.text = "Неверный ответ"
                }
                currentQuestion++
                if (currentQuestion < totalQuestions) {
                    generateQuestion(textViewQuestion)
                } else {
                    textViewQuestion.text = "Тест завершён. Правильных ответов: ${correctAnswers * 100 / totalQuestions}%"
                    buttonSubmitAnswer.isEnabled = false
                }
            }
        }
    }

    private fun generateQuestion(textView: TextView) {
        val random1 = if (number != null && number != -1) number!! else Random.nextInt(2, 10)
        val random2 = Random.nextInt(2, 10)
        textView.text = "$random1 x $random2 = ?"
    }

    private fun calculateCorrectAnswer(): Int {
        val question = findViewById<TextView>(R.id.textViewQuestion).text.toString()
        val parts = question.split(" x ", " = ")
        return parts[0].toInt() * parts[1].toInt()
    }
}