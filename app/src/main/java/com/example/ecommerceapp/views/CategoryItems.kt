package com.example.ecommerceapp.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.ActivityCategoryItemsBinding
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.util.CategoryAdapter
import com.example.ecommerceapp.util.ProductAdapter
import com.example.ecommerceapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

// Responsible for displaying products of a selected category
@AndroidEntryPoint
class CategoryItems : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryItemsBinding
    private lateinit var productAdapter: ProductAdapter

    // getting viewmodel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up recyclerview
        productAdapter = ProductAdapter { selectedProduct ->
            // Handle product click
            onProductClick(selectedProduct)
        }
        binding.recyclerViewCategory.adapter = productAdapter

        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(this)

        // Get the category name from the intent
        var categoryName = intent.getStringExtra("CATEGORY_NAME") ?: ""


        // Fetch the products
        val result = viewModel.fetchProducts(categoryName)
        result.observe(this) { newProductList ->
            if (newProductList.isNotEmpty()) {
                // Update products
                productAdapter.submitList(newProductList)
            } else {
                // show error
                Log.v("TAGY", "Error")
            }
        }


    }

    private fun onProductClick(selectedProduct: Product) {
        // Create an Intent to start the ProductDetails activity
        val intent = Intent(this, ProductDetails::class.java)
        intent.putExtra("productTitle", selectedProduct.title)
        intent.putExtra("productPrice", selectedProduct.price)
        intent.putExtra("productImageUrl", selectedProduct.imageUrl)
        intent.putExtra("productId", selectedProduct.id)
        startActivity(intent)
    }

}
