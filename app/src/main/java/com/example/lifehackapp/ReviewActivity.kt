package com.example.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val reviewText = findViewById<TextView>(R.id.reviewText)
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val btnExit = findViewById<Button>(R.id.btnExit)

        val questions = listOf(
            Question(
                "Putting your phone in rice fixes water damage",
                false,
                "Rice does not effectively remove moisture."
            ),
            Question(
                "Using a straw can prevent drinks from staining teeth",
                true,
                "Reduces contact with teeth."
            ),
            Question(
                "Cracking knuckles causes arthritis",
                false,
                "No scientific evidence supports this."
            )
        )
        val builder = StringBuilder()

        for (q in questions) {
            builder.append(".${q.statement}\n")
            builder.append("Answer: ${if (q.isHack) "Hack" else "Myth"}\n")
            builder.append("Explanation: ${q.explanation}\n\n")
        }
        reviewText.text = builder.toString()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnRestart.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()//closes Review screen so user cant go back to it
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}