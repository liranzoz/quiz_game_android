package com.example.quiz_game

import WhoAmI
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import java.util.jar.Attributes


class MainActivity : AppCompatActivity() {

    lateinit var celebImg : ImageView
    lateinit var gameManager : GameManager
    lateinit var people : List<WhoAmI>
    lateinit var btnSinger : Button
    lateinit var btnModel : Button
    lateinit var btnActor : Button
    lateinit var btnAthlete : Button
    lateinit var clickSound : SoundPool
    var clickSoundId : Int = 0
    lateinit var icHeart1 : ImageView
    lateinit var icHeart2 : ImageView
    lateinit var icHeart3 : ImageView
    lateinit var pointView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        loudSoundPool()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gameManager = GameManager()

        people = gameManager.generatePeople()
        celebImg = findViewById<ImageView>(R.id.who_am_i_pic)
        pointView = findViewById<TextView>(R.id.txt_pnt)

        btnSinger = findViewById<Button>(R.id.btnSinger)
        btnSinger.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Singer")
            if (isCorrect){
                clickSound.play(clickSoundId, 1.0f,1.0f,0,0,1.0f)
                pointView.text = gameManager.getPoints().toString()
            }else(showHeart())
            gameManager.moveToNextQ()
            refreshGame()
        }

        btnModel = findViewById<Button>(R.id.btnModel)
        btnModel.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Model")
            if (isCorrect){
                clickSound.play(clickSoundId, 1.0f,1.0f,0,0,1.0f)
                pointView.text = gameManager.getPoints().toString()
            }else{showHeart()}
            gameManager.moveToNextQ()
            refreshGame()
        }

        btnActor = findViewById<Button>(R.id.btnActor)
        btnActor.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Actor")
            if (isCorrect){
                clickSound.play(clickSoundId, 1.0f,1.0f,0,0,1.0f)
                pointView.text = gameManager.getPoints().toString()
            }else(showHeart())
            gameManager.moveToNextQ()
            refreshGame()}

        btnAthlete = findViewById<Button>(R.id.btnAthlete)
        btnAthlete.setOnClickListener{
            val isCorrect = gameManager.checkAnswer("Athlete")
            if (isCorrect){
                clickSound.play(clickSoundId, 1.0f,1.0f,0,0,1.0f)
                pointView.text = gameManager.getPoints().toString()
            }else{showHeart()}
            gameManager.moveToNextQ()
            refreshGame()}

        refreshGame()
    }

    fun refreshGame(){
        if (gameManager.isGameOver()){
            Toast.makeText(this,"Game Over!", Toast.LENGTH_LONG).show()
        }

        val currCeleb = people[gameManager.getIndex()]
        celebImg.setImageResource(currCeleb.image)
    }

    fun loudSoundPool(){
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        clickSound = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(attributes)
            .build()

         clickSoundId = clickSound.load(this, R.raw.low_battery_charge, 1)
    }

    fun showHeart(){
        icHeart1 = findViewById<ImageView>(R.id.hrt1)
        icHeart2 = findViewById<ImageView>(R.id.hrt2)
        icHeart3 = findViewById<ImageView>(R.id.hrt3)

        if (gameManager.getLifeCounter() == 2){
            icHeart3.isVisible = false
        }
        if (gameManager.getLifeCounter() == 1){
            icHeart2.isVisible = false
            Toast.makeText(this, "Your last Chance!", Toast.LENGTH_SHORT).show()
        }
        if (gameManager.getLifeCounter() == 0){
            icHeart1.isVisible = false
            Toast.makeText(this, "Your lost!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clickSound.release()
    }
}