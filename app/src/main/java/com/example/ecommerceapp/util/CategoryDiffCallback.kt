package com.example.ecommerceapp.util

import androidx.recyclerview.widget.DiffUtil
import com.example.ecommerceapp.model.Category

// used to efficiently update the RecyclerView
// when the data changes
// This is part of DiffUtil, which helps optimize list
// updates instead of reloading everything
// DiffUtil helps calculate the differences between the old &
// new lists efficiently
class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        // checks if 2 category items represent the same entity
        // if the same category appears in both old and new lists
        // DiffUtil won't reload the entire row, only updating
        // its contents if needed
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        // Checks if the content of an item has changed

        // if a category's name remains the same but its image
        // changes, DiffUtil only updates that image instead
        // of reloading everything
        return oldItem == newItem
    }

}