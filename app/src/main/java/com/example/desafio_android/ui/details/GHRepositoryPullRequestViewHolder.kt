package com.example.desafio_android.ui.details

import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.databinding.ItemPullRequestBinding


class GHRepositoryPullRequestViewHolder(
    private var binding: ItemPullRequestBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(gHJavaRepositoryPullRequestDO: GHJavaRepositoryPullRequestDO) {
        binding.pullRequestItem = gHJavaRepositoryPullRequestDO
        binding.executePendingBindings()
    }
}