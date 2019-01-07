package com.example.everj.a411_application_2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class ContactAdapter internal constructor(context: Context)
    : RecyclerView.Adapter<ContactAdapter.WordViewHolder>() {

    //Inflate the adapter from context passed
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>() // Cached copy of words
    private var listener: OnItemClickListener? = null

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView) //recyclerview textview

    }

    //Inflate viewholder with reclyerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    //SET TEXT HERE FOR FIRST NAME
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = contacts[position]
        holder.wordItemView.setText(current.first)
        holder.wordItemView.setOnClickListener {
            listener?.onItemClick(contacts.get(position))
        }


    }

    //function hidden from UI to notify data change in table
    internal fun setWords(words: List<Contact>) {
        this.contacts = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = contacts.size

    //Interface to pass current position of the contact info from the table when a user clicks on it from main activity
    interface OnItemClickListener {
        fun onItemClick(note: Contact)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}
