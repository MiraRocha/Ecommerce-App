package com.example.ecommerceapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.ItemCartBinding
import com.example.ecommerceapp.model.Product

class CartAdapter(private val onRemoveClick: (Product) -> Unit) 
    : ListAdapter<Product, CartAdapter.CartViewHolder>(CartDiffCallback()) {

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = getItem(position)

        holder.binding.cartItemTitle.text = product.title
        holder.binding.cartItemPrice.text = product.price.toString()

        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.binding.cartItemImage)

        holder.binding.removeCartItemButton.setOnClickListener {
            onRemoveClick(product)
        }
    }

    class CartDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
