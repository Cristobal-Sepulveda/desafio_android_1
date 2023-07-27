package com.example.desafio_android.utils

import androidx.recyclerview.widget.DiffUtil

class DiffCallBackProvider<T : Identifiable>: DiffUtil.ItemCallback<T>(){
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }
}

interface Identifiable {
    val id: Long
}