package com.example.desafio_android.presentation.adapters.ghjavarepository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.desafio_android.domain.GHJavaRepositoryDO
import com.example.desafio_android.databinding.ItemGithubRepositoryBinding
import com.example.desafio_android.presentation.viewmodels.HomeViewModel
import com.example.desafio_android.utils.DiffCallBackProvider


class GHJavaRepositoryAdapter(
    //private val _viewModel: HomeViewModel,
    private val delegate: GHJavaRepositoryAdapterDelegate,
    diffCallBack: DiffCallBackProvider<GHJavaRepositoryDO>
) : PagingDataAdapter<GHJavaRepositoryDO, GHJavaRepositoryViewHolder>(diffCallBack) {

    interface GHJavaRepositoryAdapterDelegate{
        fun onRepositoryClicked(gHJavaRepository: GHJavaRepositoryDO)
    }

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
        val gHJavaRepository = getItem(position)
        holder.bind(gHJavaRepository!!)
        holder.itemView.setOnClickListener{
            delegate.onRepositoryClicked(gHJavaRepository)
        }
        /*holder.itemView.setOnClickListener{
            _viewModel.displayGitHubJavaRepositoryDetails(gHJavaRepository)
        }*/
    }

}
