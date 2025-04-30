package com.example.passwordmanager

import android.app.Application
import com.example.passwordmanager.encryption.EncryptionUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        EncryptionUtils.generateAESKeyIfNeeded()
    }
}