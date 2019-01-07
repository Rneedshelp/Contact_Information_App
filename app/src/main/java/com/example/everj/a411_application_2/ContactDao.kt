package com.example.everj.a411_application_2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao {

    @Query("SELECT * from contact_Table ORDER BY contact DESC")
    fun getAlphabetizedWords(): LiveData<List<Contact>>

    @Insert //ACTS AS AN INSERT FOR NEW CONTACTS AND REPLACEMENT CONTACTS
    fun insertF(firstname:Contact)

    @Delete //DELETES
    fun update(firstname: Contact)

    //DELETE THE ENTIRE DATABASE
    @Query("DELETE FROM contact_Table")
    fun deleteAll()


}