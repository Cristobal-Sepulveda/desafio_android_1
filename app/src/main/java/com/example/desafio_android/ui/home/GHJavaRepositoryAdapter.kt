package com.example.desafio_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.databinding.ItemGithubRepositoryBinding


class GHJavaRepositoryAdapter(
    private val _viewModel: HomeViewModel
) : PagingDataAdapter<GHJavaRepositoryDO, GHJavaRepositoryViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GHJavaRepositoryViewHolder {
        return GHJavaRepositoryViewHolder(
            ItemGithubRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GHJavaRepositoryViewHolder, position: Int) {
        val gitHubJavaRepository = getItem(position)
        holder.bind(gitHubJavaRepository!!)
        holder.itemView.setOnClickListener{
            _viewModel.displayGitHubJavaRepositoryDetails(gitHubJavaRepository)
        }
    }

    object DiffCallBack: DiffUtil.ItemCallback<GHJavaRepositoryDO>(){
        override fun areItemsTheSame(oldItem: GHJavaRepositoryDO, newItem: GHJavaRepositoryDO): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GHJavaRepositoryDO, newItem: GHJavaRepositoryDO): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
