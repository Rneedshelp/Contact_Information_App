package com.example.everj.a411_application_2


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Edit_Activity : AppCompatActivity() {

    private lateinit var first: EditText
    private lateinit var last: EditText
    private lateinit var mail: EditText
    private lateinit var phone: EditText
    private lateinit var ssn : EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.replace_activity)
        first = findViewById(R.id.fname)
        last = findViewById(R.id.lname)
        mail = findViewById(R.id.email_addr)
        phone = findViewById(R.id.phonenum)
        ssn = findViewById(R.id.soc_sec)
        val save = findViewById<Button>(R.id.button_save)
        val delete = findViewById<Button>(R.id.button_delete)
        save.setOnClickListener {
            val replyIntent = Intent() //Grab Intent from main
            if (first.text.isEmpty() || last.text.isEmpty() || ssn.text.isEmpty()
                || mail.text.isEmpty() || phone.text.isEmpty())
            {       //ALL FIELDS MUST BE PROVIDED WITH A VALUE TO BE STORED
                Toast.makeText(
                    applicationContext,
                    "ERROR: All Fields Must Be Filled",
                    Toast.LENGTH_LONG
                ).show()
            }
            else
            { //with primary key being Name, the info gets stored along with it so the database has less work to do keep track of all the info.
                val contact = "Name: " + first.text.toString() + " " + last.text.toString()  + " \n" +
                        "Email:" +   mail.text.toString() + " \n" +
                        "Phone: " +   phone.text.toString() + " \n" +
                        "SSN: " +     ssn.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, contact)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()

            }
        }
        delete.setOnClickListener{ //Delete the contact information
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

}