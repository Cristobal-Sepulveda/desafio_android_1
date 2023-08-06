package com.example.desafio_android.presentation.adapters.ghjavarepository

import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.domain.GHJavaRepositoryDO
import com.example.desafio_android.databinding.ItemGithubRepositoryBinding

class GHJavaRepositoryViewHolder(
    private var binding: ItemGithubRepositoryBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(gHJavaRepositoryDO: GHJavaRepositoryDO) {
        binding.gitHubJavaRepositoryItem = gHJavaRepositoryDO
        binding.executePendingBindings()
    }
}