package com.example.ecommerceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.databinding.ActivityMainBinding
import com.example.ecommerceapp.util.CategoryAdapter
import com.example.ecommerceapp.viewmodel.MyViewModel
import com.example.ecommerceapp.views.CartActivity
import com.example.ecommerceapp.views.CategoryItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter

    // getting viewmodel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cart Button
        binding.viewCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }


        // Set up recyclerview
        categoryAdapter = CategoryAdapter { categoryName ->
            // Handle category click
            onCategoryClick(categoryName)
        }

        binding.recyclerView.adapter = categoryAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Fetch the categories
        val result = viewModel.fetchCategories()
        result.observe(this) { newCategoryList ->
            if (newCategoryList.isNotEmpty()) {
                // Update categories
                categoryAdapter.submitList(newCategoryList)
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onCategoryClick(categoryName: String) {
        // Handle category click
        val intent = Intent(this, CategoryItems::class.java)
        intent.putExtra("CATEGORY_NAME", categoryName)
        startActivity(intent)

        Toast.makeText(this, "Clicked: $categoryName", Toast.LENGTH_SHORT).show()

    }
}
