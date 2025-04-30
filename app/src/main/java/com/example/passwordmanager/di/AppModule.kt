package com.example.passwordmanager.di

import android.content.Context
import com.example.passwordmanager.data.PasswordDatabase
import com.example.passwordmanager.utils.DataStoreHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ) = DataStoreHelper(context)

    @Provides
    @Singleton
    fun providePasswordDatabase(
        @ApplicationContext context: Context
    ) = PasswordDatabase.getDatabase(context)
}