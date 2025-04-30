package com.example.passwordmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.passwordmanager.data.PasswordDatabase
import com.example.passwordmanager.data.model.PasswordEntry
import com.example.passwordmanager.encryption.EncryptionUtils
import com.example.passwordmanager.encryption.EncryptionUtils.encrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    database: PasswordDatabase
): ViewModel() {

    private val dao = database.passwordDao()

    val passwords = dao.getAll().stateIn(viewModelScope, SharingStarted.Lazily, emptyList<PasswordEntry>())

    fun addPassword(account: String, username: String, password: String) {
        if (account.isNotBlank() && username.isNotBlank() && password.isNotBlank()) {
            viewModelScope.launch {
                val (encrypted, iv) = encrypt(password)
                val entry = PasswordEntry(
                    account = account,
                    username = username,
                    encryptedPassword = encrypted,
                    iv = iv
                )
                dao.insert(entry)
            }
        }
    }

    fun deletePassword(entry: PasswordEntry) = viewModelScope.launch(Dispatchers.IO) {
        dao.delete(entry)
    }

    fun updatePassword(entry: PasswordEntry?, newPass: String) = viewModelScope.launch(Dispatchers.IO) {
        if(entry == null) return@launch

        val (encrypted, iv) = encrypt(newPass)
        dao.update(
            entry.copy(
                encryptedPassword = encrypted,
                iv = iv
            )
        )
    }
}