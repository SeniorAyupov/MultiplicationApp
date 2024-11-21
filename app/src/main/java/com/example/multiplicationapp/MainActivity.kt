package com.example.multiplicationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val buttonExerciseAll = findViewById<Button>(R.id.buttonExerciseAll)
        val buttonExerciseSelective = findViewById<Button>(R.id.buttonExerciseSelective)

        buttonExerciseAll.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("mode", "all")
            startActivity(intent)
        }

        buttonExerciseSelective.setOnClickListener {
            val number = editTextNumber.text.toString().toIntOrNull()
            if (number != null && number in 2..9) {
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("mode", "selective")
                intent.putExtra("number", number)
                startActivity(intent)
            } else {
                editTextNumber.error = "Введите число от 2 до 9"
            }
        }
    }
}