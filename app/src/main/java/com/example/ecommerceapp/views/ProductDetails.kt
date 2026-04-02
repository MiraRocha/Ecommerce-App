package com.example.ecommerceapp.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityProductDetailsBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetails : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get te product data from intent
        val productTitle = intent.getStringExtra("productTitle") ?: ""
        val productPrice = intent.getDoubleExtra("productPrice", 0.0)
        val productImageUrl = intent.getStringExtra("productImageUrl") ?: ""
        val productId = intent.getIntExtra("productId", 0)
        // Display the product Details
        binding.productTitleDetail.text = productTitle
        binding.productPriceDetail.text = "$productPrice €"

        // Load the product image using Glide
        Glide.with(this)
            .load(productImageUrl)
            .into(binding.productImageDetail)

        // Handle click event on Cart Button
        binding.addToCartButton.setOnClickListener {
            addToCart(Product(productId, productTitle, productPrice, productImageUrl))
        }
    }

    fun addToCart(product: Product) {
        // Insert item into ROOM database
        viewModel.addToCart(product)
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
    }

}