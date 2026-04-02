package com.example.ecommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",

)
