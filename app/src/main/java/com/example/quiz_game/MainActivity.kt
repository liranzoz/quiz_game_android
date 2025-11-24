package com.example.quiz_game

import WhoAmI
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {

    lateinit var celebImg : ImageView
    lateinit var  gameManager : GameManager
    lateinit var people : List<WhoAmI>
    lateinit var btnSinger : Button
    lateinit var btnModel : Button
    lateinit var btnActor : Button
    lateinit var btnAthlete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gameManager = GameManager()

        people = gameManager.generatePeople()
        celebImg = findViewById<ImageView>(R.id.who_am_i_pic)

        btnSinger = findViewById<Button>(R.id.btnSinger)
        btnSinger.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Singer")
            gameManager.moveToNextQ()
            refreshGame()
        }

        btnModel = findViewById<Button>(R.id.btnModel)
        btnModel.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Model")
            gameManager.moveToNextQ()
            refreshGame()
        }

        btnActor = findViewById<Button>(R.id.btnActor)
        btnActor.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Actor")
            gameManager.moveToNextQ()
            refreshGame()}

        btnAthlete = findViewById<Button>(R.id.btnAthlete)
        btnAthlete.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Athlete")
            gameManager.moveToNextQ()
            refreshGame()}

        refreshGame()
    }

    fun refreshGame(){
        if (gameManager.isGameOver()){
            Toast.makeText(this,"Game Over!", Toast.LENGTH_LONG).show()
        }

        val currCeleb = people[gameManager.index]
        celebImg.setImageResource(currCeleb.image)
    }
}