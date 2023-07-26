package com.example.desafio_android.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.databinding.ItemPullRequestBinding


class GHRepositoryPullRequestAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<GHJavaRepositoryPullRequestDO, GHRepositoryPullRequestViewHolder>(DiffCallBack) {



    override fun onBindViewHolder(
        holder: GHRepositoryPullRequestViewHolder,
        position: Int
    ) {
        val repositoryPullRequest = getItem(position)
        holder.bind(repositoryPullRequest)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(repositoryPullRequest)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): GHRepositoryPullRequestViewHolder {
        return GHRepositoryPullRequestViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object DiffCallBack: DiffUtil.ItemCallback<GHJavaRepositoryPullRequestDO>(){
        override fun areItemsTheSame(
            oldItem: GHJavaRepositoryPullRequestDO,
            newItem: GHJavaRepositoryPullRequestDO
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: GHJavaRepositoryPullRequestDO,
            newItem: GHJavaRepositoryPullRequestDO
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(
        val clickListener: (gHJavaRepositoryPullRequestDO: GHJavaRepositoryPullRequestDO) -> Unit
    ) {
        fun onClick(
            gHJavaRepositoryPullRequestDO: GHJavaRepositoryPullRequestDO
        ) = clickListener(gHJavaRepositoryPullRequestDO)
    }
}
