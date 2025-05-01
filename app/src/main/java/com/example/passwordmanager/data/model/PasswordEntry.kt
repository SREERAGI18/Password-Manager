package com.example.passwordmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passwords")
data class PasswordEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val account: String,
    val username: String,
    val encryptedPassword: String,
    val iv: String
)
