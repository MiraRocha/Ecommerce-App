package com.example.ecommerceapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ecommerceapp.model.Product

// Define methods for interacting with database
@Dao
interface CartDao {

    @Insert
    suspend fun addToCart(cartItem: Product)

    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<Product>

    @Query("DELETE FROM cart_items WHERE id = :productId")
    suspend fun removeFromCart(productId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}

