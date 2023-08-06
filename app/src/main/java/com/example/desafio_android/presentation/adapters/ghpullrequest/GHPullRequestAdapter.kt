package com.example.desafio_android.presentation.adapters.ghpullrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.desafio_android.domain.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.databinding.ItemPullRequestBinding
import com.example.desafio_android.utils.DiffCallBackProvider


class GHPullRequestAdapter(
    private val onClickListener: OnClickListener,
    diffCallBack: DiffCallBackProvider<GHJavaRepositoryPullRequestDO>
) : ListAdapter<GHJavaRepositoryPullRequestDO, GHPullRequestViewHolder>(diffCallBack) {



    override fun onBindViewHolder(
        holder: GHPullRequestViewHolder,
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
    ): GHPullRequestViewHolder {
        return GHPullRequestViewHolder(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class OnClickListener(
        val clickListener: (gHJavaRepositoryPullRequestDO: GHJavaRepositoryPullRequestDO) -> Unit
    ) {
        fun onClick(
            gHJavaRepositoryPullRequestDO: GHJavaRepositoryPullRequestDO
        ) = clickListener(gHJavaRepositoryPullRequestDO)
    }
}
