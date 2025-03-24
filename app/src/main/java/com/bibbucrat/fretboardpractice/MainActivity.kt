package com.bibbucrat.fretboardpractice

import android.os.Bundle
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bibbucrat.fretboardpractice.ui.theme.FretboardPracticeTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)

        val random = Random()
        var randomString:Int
        var randomNote:Int

        val notes = arrayListOf("A" ,"B♭/A♯", "B/C♭", "C", "C♯/D♭", "D", "E♭/D♯", "E", "F", "F♯/G♭", "G", "G♯/A♭")
        var notUsedNotes = notes.clone() as ArrayList<String>
        val strings = mutableSetOf(1, 2, 3, 4, 5, 6)
        var currentStrings = strings.toSet()

        val map = mapOf(*currentStrings.map {it to notes}.toTypedArray())
        var fretboard = map.mapValues { it.value.toMutableList() }

        val outputText = findViewById<TextView>(R.id.output)
        //val scoreText = findViewById<TextView>(R.id.score)


        val genButton = findViewById<Button>(R.id.gen)
        val stringsButton = findViewById<Button>(R.id.strings)
        val resetButton = findViewById<Button>(R.id.clear)

        val popupMenu = PopupMenu(this, stringsButton)

        popupMenu.menu.setGroupCheckable(R.id.string_group, true, false)

        popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)

        val stringsChecked = arrayListOf(popupMenu.menu.findItem(R.id.string1),
            popupMenu.menu.findItem(R.id.string2),
            popupMenu.menu.findItem(R.id.string3),
            popupMenu.menu.findItem(R.id.string4),
            popupMenu.menu.findItem(R.id.string5),
            popupMenu.menu.findItem(R.id.string6))
        var usedStrings = 6;

        popupMenu.setOnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.string1 -> {
                    item.isChecked = !item.isChecked

                    if (!item.isChecked) {
                        strings.remove(1)
                        usedStrings--
                    }
                    if (item.isChecked) {
                        strings.add(1)
                        usedStrings++
                    }
                }
                R.id.string2 -> {
                    item.isChecked = !item.isChecked

                    if (!item.isChecked) {
                        strings.remove(2)
                        usedStrings--
                    }
                    if (item.isChecked) {
                        strings.add(2)
                        usedStrings++
                    }
                }
                R.id.string3 -> {
                    item.isChecked = !item.isChecked

                    if (!item.isChecked) {
                        strings.remove(3)
                        usedStrings--
                    }
                    if (item.isChecked) {
                        strings.add(3)
                        usedStrings++
                    }
                }
                R.id.string4 -> {
                    item.isChecked = !item.isChecked

                    if (!item.isChecked) {
                        strings.remove(4)
                        usedStrings--
                    }
                    if (item.isChecked) {
                        strings.add(4)
                        usedStrings++
                    }
                }
                R.id.string5 -> {
                    item.isChecked = !item.isChecked

                    if (!item.isChecked) {
                        strings.remove(5)
                        usedStrings--
                    }
                    if (item.isChecked) {
                        strings.add(5)
                        usedStrings++
                    }
                }
                R.id.string6 -> {
                    item.isChecked = !item.isChecked

                    if (!item.isChecked) {
                        strings.remove(6)
                        usedStrings--
                    }
                    if (item.isChecked) {
                        strings.add(6)
                        usedStrings++
                    }
                }
                R.id.all_none -> {
                    if(usedStrings == 6) {
                        strings.clear()
                        /*popupMenu.menu.findItem(R.id.string1).isChecked = false
                        popupMenu.menu.findItem(R.id.string2).isChecked = false
                        popupMenu.menu.findItem(R.id.string3).isChecked = false
                        popupMenu.menu.findItem(R.id.string4).isChecked = false
                        popupMenu.menu.findItem(R.id.string5).isChecked = false
                        popupMenu.menu.findItem(R.id.string6).isChecked = false */
                        stringsChecked.forEach {it.isChecked = false}
                        usedStrings=0
                    }
                    else {
                        strings.addAll(setOf(1, 2, 3, 4, 5, 6))
                        /*popupMenu.menu.findItem(R.id.string1).isChecked = true
                        popupMenu.menu.findItem(R.id.string2).isChecked = true
                        popupMenu.menu.findItem(R.id.string3).isChecked = true
                        popupMenu.menu.findItem(R.id.string4).isChecked = true
                        popupMenu.menu.findItem(R.id.string5).isChecked = true
                        popupMenu.menu.findItem(R.id.string6).isChecked = true */
                        stringsChecked.forEach {it.isChecked = true}
                        usedStrings=6
                    }
                }
            }
            true
        }

        /*popupMenu.menu.findItem(R.id.string1).isChecked = true
        popupMenu.menu.findItem(R.id.string2).isChecked = true
        popupMenu.menu.findItem(R.id.string3).isChecked = true
        popupMenu.menu.findItem(R.id.string4).isChecked = true
        popupMenu.menu.findItem(R.id.string5).isChecked = true
        popupMenu.menu.findItem(R.id.string6).isChecked = true */
        stringsChecked.forEach {it.isChecked = true}
        usedStrings=6

        stringsButton.setOnClickListener{
            popupMenu.show()
        }

        genButton.setOnClickListener {
            currentStrings = strings.toSet()

            if(strings.size != 0) {
                randomString = random.nextInt(currentStrings.size)
                if (fretboard[currentStrings.elementAt(randomString)]?.isEmpty() == false) {
                    randomNote =
                        random.nextInt(fretboard[currentStrings.elementAt(randomString)]?.size ?: 1)

                    outputText.text =
                        "${fretboard[currentStrings.elementAt(randomString)]?.elementAt(randomNote)} at String ${currentStrings.elementAt(randomString)}"
                    fretboard[currentStrings.elementAt(randomString)]?.removeAt(randomNote)
                    if (fretboard[currentStrings.elementAt(randomString)]?.isEmpty() == true) {
                        strings.remove(currentStrings.elementAt(randomString))
                    }
                } else {
                    strings.remove(currentStrings.elementAt(randomString))
                }
            }
            else {
                /*if(notUsedNotes.size != 0) {
                    randomNote = random.nextInt(notUsedNotes.size)
                    outputText.text = notUsedNotes.elementAt(randomNote)
                    notUsedNotes.removeAt(randomNote)
                }
                else {
                    outputText.text = "No more notes. Click Reset."
                } */

                if(usedStrings==0 && notUsedNotes.size != 0){
                    randomNote = random.nextInt(notUsedNotes.size)
                    outputText.text = notUsedNotes.elementAt(randomNote)
                    notUsedNotes.removeAt(randomNote)
                }
                else {
                    outputText.text = "No more notes. Click Reset."
                }
            }
        }

        resetButton.setOnClickListener {
            notUsedNotes = notes.clone() as ArrayList<String>
            fretboard = map.mapValues { it.value.toMutableList() }
            if(popupMenu.menu.findItem(R.id.string1).isChecked){
                strings.add(1)
            }
            if(popupMenu.menu.findItem(R.id.string2).isChecked){
                strings.add(2)
            }
            if(popupMenu.menu.findItem(R.id.string3).isChecked){
                strings.add(3)
            }
            if(popupMenu.menu.findItem(R.id.string4).isChecked){
                strings.add(4)
            }
            if(popupMenu.menu.findItem(R.id.string5).isChecked){
                strings.add(5)
            }
            if(popupMenu.menu.findItem(R.id.string6).isChecked){
                strings.add(6)
            }
        }


    }
}
