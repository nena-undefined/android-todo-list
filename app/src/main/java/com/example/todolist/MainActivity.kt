package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val toDoList: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addToDoContent(view: View){
        val toDoContent = findViewById<EditText>(R.id.editTextToDo)
        val content = toDoContent.text.toString()

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = textView.text.toString() + content + "\n"
    }

}