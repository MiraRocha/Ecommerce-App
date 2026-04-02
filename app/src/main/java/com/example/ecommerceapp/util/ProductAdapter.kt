package com.example.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemCategoryBinding
import com.example.ecommerceapp.databinding.ItemProductBinding
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.util.CategoryAdapter.CategoryViewHolder

class ProductAdapter(private val onProductClick: (Product) -> Unit)
    : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        val productImg = binding.productImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.productTitle.text = product.title
        holder.binding.productPrice.text = product.price.toString()


        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.productImg)


        holder.itemView.setOnClickListener {
            onProductClick(product)
        }
    }



}