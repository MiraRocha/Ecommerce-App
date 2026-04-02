package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val cartItems = MutableLiveData<List<Product>>()

    // Repository dependency is injected into
    // MyViewModel constructor

    // Fetch Categories
    fun fetchCategories(): MutableLiveData<List<Category>> {
        return repository.fetchCategories()
    }

    // Fetch Products
    fun fetchProducts(categoryName: String): MutableLiveData<List<Product>> {
        return repository.fetchProducts(categoryName)
    }

    // Add to Cart
    fun addToCart(cartItem: Product) = viewModelScope.launch {
        repository.addToCart(cartItem)
        getCartItems()
    }
    // Get Cart Items
    fun getCartItems() : MutableLiveData<List<Product>> {
        viewModelScope.launch {
            val items = repository.getCartItems()
            cartItems.postValue(items)
        }
        return cartItems
    }


    // Remove from Cart
    fun removeFromCart(productId: Int) = viewModelScope.launch {
        repository.removeFromCart(productId)
        getCartItems()
    }
    // Clear Cart
    fun clearCart() =  viewModelScope.launch {
        repository.clearCart()
        getCartItems()
    }

    fun savePurchasesInFirestore(product: Product){
        repository.savePurchasesInFirestore(product)
    }

}
