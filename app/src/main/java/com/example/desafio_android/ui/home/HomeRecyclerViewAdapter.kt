package com.example.desafio_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.databinding.ItemGithubRepositoryBinding


class HomeRecyclerViewAdapter(private val _viewModel: HomeViewModel
) : ListAdapter<GHJavaRepositoryDTO, HomeRecyclerViewAdapter.HomeRecyclerViewViewHolder>(
    DiffCallBack) {

    class HomeRecyclerViewViewHolder(private var binding: ItemGithubRepositoryBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(GHJavaRepositoryDTO: GHJavaRepositoryDTO) {
            binding.gitHubJavaRepositoryItem = GHJavaRepositoryDTO
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewViewHolder, position: Int) {
        val gitHubJavaRepository = getItem(position)
        holder.bind(gitHubJavaRepository)
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

    object DiffCallBack: DiffUtil.ItemCallback<GHJavaRepositoryDTO>(){
        override fun areItemsTheSame(oldItem: GHJavaRepositoryDTO, newItem: GHJavaRepositoryDTO): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GHJavaRepositoryDTO, newItem: GHJavaRepositoryDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
