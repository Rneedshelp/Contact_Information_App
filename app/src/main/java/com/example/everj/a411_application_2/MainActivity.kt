//RICHARD ECHEVERRIA
///CWID 891720195
//RECHEVERRIA04@CSU.FULLERTON.EDU
//CPSC 411 APPLICATION 2
package com.example.everj.a411_application_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var contactVM: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ContactAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        contactVM = ViewModelProviders.of(this).get(ContactViewModel::class.java)

        // Update table if delete, new or replace
        contactVM.allContacts.observe(this, Observer { contacts ->
            contacts?.let { adapter.setWords(it) }
        })
        var contacts = emptyList<Contact>()

        val addbutton = findViewById<FloatingActionButton>(R.id.addbutton)
        val clearall = findViewById<FloatingActionButton>(R.id.delete_button)

        //LISTEN FOR BUTTON OR CONTACT WIDGET CLICKS
        addbutton.setOnClickListener {
            val intent = Intent(this, New_Activity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
        //BEING ACTIVITY FOR A NEW WORD
        //adapater is used to call on the value of the interface passing the clicked on item position
        adapter.setOnItemClickListener(object : ContactAdapter.OnItemClickListener{
            override fun onItemClick(note: Contact) {
                contactVM.update(note)
                val intent =Intent(this@MainActivity,Edit_Activity::class.java)
                startActivityForResult(intent,newWordActivityRequestCode).toString()
            }
        })
        clearall.setOnClickListener{
            val intent = Intent(this,Delete_Activity::class.java)
            startActivity(intent)

        }


    }

    //fetches result from request and result code
    //Learned this thanks via to codelabs
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        if (requestCode == newWordActivityRequestCode && resultCode == RESULT_OK){
            intentData?.let { data ->
                val first = Contact(data.getStringExtra(New_Activity.EXTRA_REPLY))
                contactVM.insertF(first)
            }!!
        }
        else if(requestCode == newWordActivityRequestCode && resultCode == RESULT_OK){
            intentData?.let { data ->
                val first = Contact(data.getStringExtra(Edit_Activity.EXTRA_REPLY))
                contactVM.insertF(first)
            }
        }
    }



}

