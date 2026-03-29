package com.example.lifehackapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0
    private var answered = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        val questionText = findViewById<TextView>(R.id.questionText)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val trueBtn = findViewById<Button>(R.id.trueBtn)
        val falseBtn = findViewById<Button>(R.id.falseBtn)
        val nextBtn = findViewById<Button>(R.id.nextBtn)

        questions = listOf(
            Question("Putting your phone in rice fixes water damage", false, "Rice does not effectively remove moisture."),
            Question("Using a straw can prevent drinks from staining teeth", true, "It reduces contact with teeth."),
            Question("Cracking knuckles causes arthritis", false, "No scientific evidence supports this."),
            Question("Freezing bread makes it last longer", true, "It slows down mold growth.")
        )

        fun loadQuestion(){
            val q = questions [currentIndex]
            questionText.text = q.statement
            feedbackText.text = ""
            answered = false
        }

        fun checkAnswer(userAnswer: Boolean){
            if (answered) return

            val correct = questions[currentIndex].isHack

            if (userAnswer== correct) {
                feedbackText.text = "Correct! ${questions[currentIndex].explanation}"
                score++
            } else {
                feedbackText.text = "Wrong! ${questions[currentIndex].explanation}"
            }
            answered = true
        }

        trueBtn.setOnClickListener { checkAnswer(true) }
        falseBtn.setOnClickListener { checkAnswer(false) }

        nextBtn.setOnClickListener {
            currentIndex++

            if (currentIndex < question.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
            }
        }
loadQuestion()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("Score", score)
            startActivity(intent)
        }
    }
}