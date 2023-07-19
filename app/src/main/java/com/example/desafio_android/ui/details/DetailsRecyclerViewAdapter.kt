package com.example.desafio_android.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.data.dto.GitHubJavaRepositoryPullRequests
import com.example.desafio_android.databinding.ItemPullRequestBinding


class DetailsRecyclerViewAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<
        GitHubJavaRepositoryPullRequests,
        DetailsRecyclerViewAdapter.DetailsRecyclerViewViewHolder
    >(DiffCallBack) {

    class DetailsRecyclerViewViewHolder(
        private var binding: ItemPullRequestBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(gitHubJavaRepositoryPullRequests: GitHubJavaRepositoryPullRequests) {
            binding.pullRequestItem = gitHubJavaRepositoryPullRequests
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: DetailsRecyclerViewViewHolder, position: Int) {
        val repositoryPullRequest = getItem(position)
        holder.bind(repositoryPullRequest)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(repositoryPullRequest)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsRecyclerViewViewHolder {
        return DetailsRecyclerViewViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object DiffCallBack: DiffUtil.ItemCallback<GitHubJavaRepositoryPullRequests>(){
        override fun areItemsTheSame(oldItem: GitHubJavaRepositoryPullRequests,
                                     newItem: GitHubJavaRepositoryPullRequests): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GitHubJavaRepositoryPullRequests,
                                        newItem: GitHubJavaRepositoryPullRequests): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (gitHubJavaRepositoryPullRequests: GitHubJavaRepositoryPullRequests) -> Unit) {
        fun onClick(gitHubJavaRepositoryPullRequests: GitHubJavaRepositoryPullRequests) = clickListener(gitHubJavaRepositoryPullRequests)
    }
}
