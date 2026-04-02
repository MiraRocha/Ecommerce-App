package com.example.ecommerceapp.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ecommerceapp.R
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.room.CartDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val cartDao: CartDao
) {
    // Firebase: Fetch Categories from Firestore
    fun fetchCategories(): MutableLiveData<List<Category>> {
        val categoriesList = MutableLiveData<List<Category>>()

        val catImages = mapOf(
            "Electronics" to R.drawable.electronics,
            "Jewelery" to R.drawable.jewelery,
            "Men" to R.drawable.mensclothing,
            "Women" to R.drawable.womenclothing,
            "Cosmetics" to R.drawable.cosmetics,
            "Shoes" to R.drawable.runningshoes,
            "Toys" to R.drawable.toys,
            "Tools" to R.drawable.tools,
            "Home" to R.drawable.sofa,
            "Automotive" to R.drawable.brake
        )

        firestore.collection("categories")
            .get()
            .addOnSuccessListener { documents ->
                val list = documents.map { document ->
                    // Use "name" field or document ID
                    val name = document.getString("name") ?: document.id

                    val imageRes = catImages[name] ?: R.drawable.ic_launcher_background

                    Log.d("REPO", "Category: $name, ImageRes: $imageRes")
                    Category(name = name, catImg = imageRes)
                }

                categoriesList.postValue(list)
            }
            .addOnFailureListener { e ->
                Log.e("REPO", "Error fetching categories", e)
                categoriesList.postValue(emptyList())
            }

        return categoriesList
    }


    // Firebase: Fetch Products from Firestore
    fun fetchProducts(categoryName: String): MutableLiveData<List<Product>> {
        val productsList = MutableLiveData<List<Product>>()

        // Fetching data from firestore
        firestore.collection("categories")
            .document(categoryName)
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val products = documents.map { document ->
                    Product(
                        id = try { document.id.toInt() } catch (e: Exception) { 0 },
                        title = document.getString("title") ?: "",
                        price = document.getDouble("price") ?: 0.0,
                        imageUrl = document.getString("imageUrl") ?: ""
                    )
                }

                // Updating LiveData & Logging
                productsList.postValue(products)
                Log.v("TAGY", "products: $products")
            }
            .addOnFailureListener { exception ->
                Log.e("TAGY", "Exception: $exception")
            }
        return productsList

    }

    // Room: Add Product to Cart
    suspend fun addToCart(product: Product) {
        cartDao.addToCart(product)
    }

    // Room: Get Cart Items
    suspend fun getCartItems(): List<Product> {
        return cartDao.getCartItems()
    }

    // Room: Remove Product from Cart
    suspend fun removeFromCart(productId: Int) {
        cartDao.removeFromCart(productId)
    }

    // Room: Clear Cart
    suspend fun clearCart() {
        cartDao.clearCart()
    }

    // Upload Product to Firestore
    fun savePurchasesInFirestore(product: Product){
        firestore.collection("purchases")
            .add(product)
            .addOnSuccessListener {
                // Clearing the cart
                CoroutineScope(Dispatchers.IO).launch {
                    clearCart()
                }
            }
            .addOnFailureListener {

            }
    }
}
