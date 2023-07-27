package com.example.desafio_android.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO

/*class DiffCallBackProvider: DiffUtil.ItemCallback<GHJavaRepositoryDO>(){
    override fun areItemsTheSame(oldItem: GHJavaRepositoryDO, newItem: GHJavaRepositoryDO): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: GHJavaRepositoryDO, newItem: GHJavaRepositoryDO): Boolean {
        return oldItem.id == newItem.id
    }
}*/

class DiffCallBackProvider<T : Identifiable>: DiffUtil.ItemCallback<T>(){
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }
}

interface Identifiable {
    val id: Long // Change the type of 'id' to match the actual type of your id property.
}