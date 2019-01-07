package com.example.everj.a411_application_2

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ContactRepository (private val wordDao: ContactDao) {

    val allWords: LiveData<List<Contact>> = wordDao.getAlphabetizedWords()

    //Worker threads that help call functions
    @WorkerThread
    fun insertF(first: Contact) {
        wordDao.insertF(first) ///WordDAO.kt insertF function
    }

    @WorkerThread
    fun update(first: Contact) {
        wordDao.update(first)
    }

    @WorkerThread
    fun deleteall() {
        wordDao.deleteAll()
    }
}