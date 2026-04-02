package com.example.ecommerceapp.util

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}