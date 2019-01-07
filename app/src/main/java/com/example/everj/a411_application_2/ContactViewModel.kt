package com.example.everj.a411_application_2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContactViewModel(application: Application) : AndroidViewModel(application) {

    //Use wrapper methods to call on function via Dispatchers to hide calls from the UI
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    init {
        val wordsDao = ContactDatabase.getDatabase(application,scope ).wordDao()
        repository = ContactRepository(wordsDao)
        allContacts = repository.allWords
    }

    fun insertF(first: Contact) = scope.launch(Dispatchers.IO) {
        repository.insertF(first) //call on repository insert function
    }

    fun update(first: Contact) = scope.launch(Dispatchers.IO) {
        repository.update(first) //cal on update function which acts as both insert and delete
    }

    fun deleteall() = scope.launch(Dispatchers.IO) {
        repository.deleteall()
    }
 //CANCEL LONG RUNNING JOBS
    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}