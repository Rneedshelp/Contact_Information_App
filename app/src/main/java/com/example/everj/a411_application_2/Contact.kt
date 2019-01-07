package com.example.everj.a411_application_2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact_Table") //Initial contact information table
data class Contact(@PrimaryKey @ColumnInfo(name = "contact") val first: String)
