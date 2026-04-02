package com.example.ecommerceapp.di

import android.content.Context
import com.example.ecommerceapp.room.CardDatabase
import com.example.ecommerceapp.room.CartDao
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // define how dependencies are provided
@InstallIn(SingletonComponent::class) // define the scope of the dependencies
object FirebaseModule {
    // Provide a singleton instance of FirebaseFirestore
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()

    }

    // Provide Room Database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CardDatabase {
        return CardDatabase.getDatabase(context)
    }

    // Provide CartDao
    @Provides
    fun provideCartDao(database: CardDatabase): CartDao {
        return database.cartDao()
    }
}
