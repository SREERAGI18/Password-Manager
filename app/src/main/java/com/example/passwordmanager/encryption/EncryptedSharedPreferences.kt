package com.example.passwordmanager.encryption

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

object EncryptedSharedPreferenceManager {

    private const val USER_PIN = "user_pin"

    fun getSecurePrefs(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun verifyPin(context: Context, inputPin: String): Boolean {
        val prefs = getSecurePrefs(context)
        val savedPin = prefs.getString(USER_PIN, null)
        return savedPin == inputPin
    }

    fun isPinSet(context: Context): Boolean {
        val prefs = getSecurePrefs(context)
        return prefs.contains(USER_PIN)
    }

    fun savePin(context: Context, pin: String) {
        val prefs = getSecurePrefs(context)
        prefs.edit() { putString(USER_PIN, pin) }
    }

}