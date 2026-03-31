package com.example.lifehackapp

import android.os.Bundle
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

        val questions = listOf(
            Question("Putting your phone in rice fixes water damage", false, "Rice does not effectively remove moisture.")
            Question("Using a straw can prevent drinks from staining teeth", true, "Reduces contact with teeth.")
            Question("Cracking knuckles causes arthritis", true,"Slows mold growth." )
        )
        val builder = StringBuilder()

        for (q in question) {
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
    }
}