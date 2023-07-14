package com.example.desafio_android.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.RepositoryPullRequest
import com.example.desafio_android.databinding.ItemGithubRepositoryBinding
import com.example.desafio_android.databinding.ItemPullRequestBinding


class DetailsRecyclerViewAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<
        RepositoryPullRequest,
        DetailsRecyclerViewAdapter.DetailsRecyclerViewViewHolder
    >(DiffCallBack) {

    class DetailsRecyclerViewViewHolder(
        private var binding: ItemPullRequestBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoryPullRequest: RepositoryPullRequest) {
            binding.pullRequestItem = repositoryPullRequest
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: DetailsRecyclerViewViewHolder, position: Int) {
        val repositoryPullRequest = getItem(position)
        holder.bind(repositoryPullRequest)
        holder.itemView.setOnClickListener{
            onClickListener.onClick()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsRecyclerViewViewHolder {
        return DetailsRecyclerViewViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object DiffCallBack: DiffUtil.ItemCallback<RepositoryPullRequest>(){
        override fun areItemsTheSame(oldItem: RepositoryPullRequest,
                                     newItem: RepositoryPullRequest): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RepositoryPullRequest,
                                        newItem: RepositoryPullRequest): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: () -> Unit) {
        fun onClick() = clickListener()
    }
}
