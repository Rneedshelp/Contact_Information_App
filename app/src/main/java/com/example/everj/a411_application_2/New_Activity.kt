package com.example.everj.a411_application_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class New_Activity : AppCompatActivity() {
    //private values for the contact info
    private lateinit var first: EditText
    private lateinit var last: EditText
    private lateinit var mail: EditText
    private lateinit var phone: EditText
    private lateinit var ssn : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_activity)
        first = findViewById(R.id.fname)
        last = findViewById(R.id.lname)
        mail = findViewById(R.id.email_addr)
        phone = findViewById(R.id.phonenum)
        ssn = findViewById(R.id.soc_sec)
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (first.text.isEmpty() || last.text.isEmpty() || ssn.text.isEmpty()
                || mail.text.isEmpty() || phone.text.isEmpty())
            {
                Toast.makeText(
                    applicationContext,
                    "ERROR: All Fields Must Be Filled",
                    Toast.LENGTH_LONG
                ).show()
            }
            else //INSERT ALL FIELDS ELSE GET ERROR
            {
                val contact = "Name: " + first.text.toString() + " " + last.text.toString()  + " \n" +
                        "Email:" +   mail.text.toString() + " \n" +
                        "Phone: " +   phone.text.toString() + " \n" +
                        "SSN: " +     ssn.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, contact)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()

            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"

    }
}