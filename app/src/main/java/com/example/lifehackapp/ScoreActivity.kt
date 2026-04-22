package com.example.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        val scoreText = findViewById<TextView>(R.id.scoreText)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val reviewBtn = findViewById<Button>(R.id.reviewBtn)
        val exitBtn = findViewById<Button>(R.id.exitBtn)
        val restartBtn = findViewById<Button>(R.id.restartBtn)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        scoreText.text = "Score: $score / $total"


        feedbackText.text =
            if (score > total / 2) "Well done!" else "Try again!"

        reviewBtn.setOnClickListener {

            val intent = Intent(this, ReviewActivity::class.java)

            startActivity(intent)
        }

        exitBtn.setOnClickListener {
            finishAffinity()
        }

        restartBtn.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()//closes Score screen so user cant go back to it
        }
    }
}
