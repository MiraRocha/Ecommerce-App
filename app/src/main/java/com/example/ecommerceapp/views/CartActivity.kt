package com.example.ecommerceapp.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.databinding.ActivityCartBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.util.CartAdapter
import com.example.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val viewModel: MyViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the adapter
        cartAdapter = CartAdapter(){
            // Pass the remove function to the adapter
            cardItem -> removeCardItem(cardItem)
        }

        binding.recyclerViewCart.apply{
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(this@CartActivity)
        }

        // Clear Cart
        binding.clearCartButton.setOnClickListener {
            viewModel.clearCart()

            // Clear the adapter's data
            cartAdapter.submitList(emptyList())
        }
        binding.checkoutOutButton.setOnClickListener {
            // Handle checkout logic
            cheackOutCart()
        }

        // Fetch cart items
        viewModel.getCartItems().observe(this) { cartItems ->
            cartAdapter.submitList(cartItems)
        }

    }

    // Removing the card item
    private fun removeCardItem(cardItem: Product) {
        viewModel.removeFromCart(cardItem.id)

        val updateCardItems = viewModel.getCartItems()
            .value?.toMutableList()

        cartAdapter.submitList(updateCardItems)
    }

    // Upload purchased items to Firestore
    private fun cheackOutCart(){
        // Get the list of purchased items
        viewModel.getCartItems().observe(this){
            purchasedItems ->

            for(item in purchasedItems){
                viewModel.savePurchasesInFirestore(item)
            }



        }

    }

}