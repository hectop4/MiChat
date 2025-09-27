package com.example.michat.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.michat.data.local.database.AppDatabase
import com.example.michat.data.remote.FirebaseMessageService
import com.example.michat.data.repository.MessageRepositoryImpl
import com.example.michat.data.repository.UserRepositoryImpl
import com.example.michat.domain.repository.MessageRepository
import com.example.michat.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "michat_database"
        ).build()
    }

    @Provides
    fun provideMessageDao(database: AppDatabase) = database.messageDao()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("michat_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository {
        return userRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideMessageRepository(
        messageDao: com.example.michat.data.local.dao.MessageDao,
        firebaseService: FirebaseMessageService,
        sharedPreferences: SharedPreferences
    ): MessageRepository {
        return MessageRepositoryImpl(messageDao, firebaseService, sharedPreferences)
    }
}
