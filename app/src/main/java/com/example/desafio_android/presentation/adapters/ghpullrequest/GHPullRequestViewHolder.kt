package com.example.desafio_android.presentation.adapters.ghpullrequest

import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.domain.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.databinding.ItemPullRequestBinding


class GHPullRequestViewHolder(
    private var binding: ItemPullRequestBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(gHJavaRepositoryPullRequestDO: GHJavaRepositoryPullRequestDO) {
        binding.pullRequestItem = gHJavaRepositoryPullRequestDO
        binding.executePendingBindings()
    }
}