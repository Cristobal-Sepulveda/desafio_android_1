package com.example.desafio_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.databinding.ItemGithubRepositoryBinding


class HomeRecyclerViewAdapter(private val _viewModel: HomeViewModel
) : PagingDataAdapter<GHJavaRepositoryDO, HomeRecyclerViewAdapter.HomeRecyclerViewViewHolder>(
    DiffCallBack) {

    class HomeRecyclerViewViewHolder(private var binding: ItemGithubRepositoryBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(GHJavaRepositoryDO: GHJavaRepositoryDO) {
            binding.gitHubJavaRepositoryItem = GHJavaRepositoryDO
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewViewHolder, position: Int) {
        val gitHubJavaRepository = getItem(position)
        holder.bind(gitHubJavaRepository!!)
        holder.itemView.setOnClickListener{
            _viewModel.displayGitHubJavaRepositoryDetails(gitHubJavaRepository)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewViewHolder {
        return HomeRecyclerViewViewHolder(
            ItemGithubRepositoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
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
