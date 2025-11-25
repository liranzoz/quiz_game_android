package com.example.quiz_game

import WhoAmI

class GameManager {

    var points: Int = 0
    var index : Int = 0
    var lifeCounter : Int = 3
    lateinit var people : List<WhoAmI>

    fun generatePeople() : List<WhoAmI> {
        val tempPeople = ArrayList<WhoAmI>()

        tempPeople.add(WhoAmI("Ariana Grande",R.drawable.ariana_grande, true,false,false,false))
        tempPeople.add(WhoAmI("Rami Malek", R.drawable.rami_malek,false,false,false,true))
        tempPeople.add(WhoAmI("Bob Odenkirk", R.drawable.bob_odenkirk, false,false,false,true))
        tempPeople.add(WhoAmI("Cristiano Ronaldo", R.drawable.crisitano_ronaldo, false, false,true,false))
        tempPeople.add(WhoAmI("Kylian Mbappe", R.drawable.kylian_mbappe,false, false,true,false))
        tempPeople.add(WhoAmI("Don Toliver", R.drawable.don_toliver, true,false,false,false))
        tempPeople.add(WhoAmI("Alex Pereira", R.drawable.alex_perrira, false, false,true,false))
        tempPeople.add(WhoAmI("Jon Jones", R.drawable.jon_jones, false, false,true,false))
        tempPeople.add(WhoAmI("Gigi Hadid", R.drawable.gigi_hadid,false,true,false,false))
        tempPeople.add(WhoAmI("Jermey Meeks", R.drawable.jermey_meeks,false,true,false,false))
        tempPeople.add(WhoAmI("BlackBear",R.drawable.blackbear, true,false,false,false))
        tempPeople.add(WhoAmI("Rick Sanchez", R.drawable.rick_sanchez,false,false,false,true))
        tempPeople.add(WhoAmI("Aaron Paul", R.drawable.aaron_paul,false,false,false,true))
        tempPeople.add(WhoAmI("Cillian Murphy", R.drawable.cillian_murphy,false,false,false,true))

        this.people = tempPeople
        return tempPeople
    }

    fun checkAnswer(answer : String): Boolean{
        val currCeleb = people[index]
        val isCorrect : Boolean
        if (currCeleb.equals("Singer")){
            isCorrect = currCeleb.isSinger
        }
        else if (currCeleb.equals("Model")){
            isCorrect = currCeleb.isModel
        }
        else if (currCeleb.equals("Athlete")){
            isCorrect = currCeleb.isAthlete
        }
        else {
            isCorrect = currCeleb.isActor
        }
        if (isCorrect){
            points++
        } else {
            lifeCounter--
        }
        return false
    }

    fun moveToNextQ(){index++}

    fun isGameOver(): Boolean {return index > people.size || lifeCounter == 0}
}