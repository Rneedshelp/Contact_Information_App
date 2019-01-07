package com.example.everj.a411_application_2


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders



class Delete_Activity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_activity)
        //brand new activity to ask user if go proceed deleting the database
        val no = findViewById<Button>(R.id.no_button)
        val yes = findViewById<Button>(R.id.yes_button)
        yes.setOnClickListener {
            val  wordViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
            wordViewModel.deleteall()
            finish()
        }
        no.setOnClickListener {
            finish()
        }
    }
}