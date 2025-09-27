package com.example.michat.config

import com.example.michat.BuildConfig

object FirebaseConfig {
    const val API_KEY = BuildConfig.FIREBASE_API_KEY
    const val PROJECT_ID = BuildConfig.FIREBASE_PROJECT_ID
    const val PROJECT_NUMBER = BuildConfig.FIREBASE_PROJECT_NUMBER
    const val STORAGE_BUCKET = BuildConfig.FIREBASE_STORAGE_BUCKET
    const val APP_ID = BuildConfig.FIREBASE_APP_ID

    fun isConfigured(): Boolean {
        return API_KEY.isNotEmpty() &&
               PROJECT_ID.isNotEmpty() &&
               PROJECT_NUMBER.isNotEmpty() &&
               STORAGE_BUCKET.isNotEmpty() &&
               APP_ID.isNotEmpty()
    }
}
