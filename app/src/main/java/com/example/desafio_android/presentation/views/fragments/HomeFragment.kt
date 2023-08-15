package com.example.desafio_android.presentation.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.desafio_android.databinding.FragmentHomeBinding
import com.example.desafio_android.domain.GHJavaRepositoryDO
import com.example.desafio_android.presentation.adapters.ghjavarepository.GHJavaRepositoryAdapter
import com.example.desafio_android.presentation.viewmodels.HomeViewModel
import com.example.desafio_android.utils.DiffCallBackProvider
import com.example.desafio_android.utils.asParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val _viewModel: HomeViewModel by viewModel()
    inner class MyGHJavaRepositoryAdapterDelegate:
        GHJavaRepositoryAdapter.GHJavaRepositoryAdapterDelegate {
        override fun onRepositoryClicked(gHJavaRepository: GHJavaRepositoryDO) {
            _viewModel.displayGitHubJavaRepositoryDetails(gHJavaRepository)
        }

    }
    private val adapter = GHJavaRepositoryAdapter(
        MyGHJavaRepositoryAdapterDelegate(),
        DiffCallBackProvider()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.homeScreenRecyclerViewListadoDeRepositories.adapter = adapter
        observeAndSubmitJavaRepositories()
        observeIfIShouldNavigate()
        handlePagingLoadState()
        _viewModel.getJavaRepositories()

        return _binding!!.root
    }

    private fun observeAndSubmitJavaRepositories() {
        _viewModel.listToDisplay.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                adapter.submitData(it)
            }
        }
    }

    private fun observeIfIShouldNavigate() {
        _viewModel.shouldINavigate.observe(viewLifecycleOwner) { gitHubJavaRepository ->
            gitHubJavaRepository?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationDetails(
                        it.asParcelable()
                    )
                )
                _viewModel.navigationCompleted()
            }
        }
    }

    private fun handlePagingLoadState() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                if (loadState.refresh is LoadState.Error) {
                    _binding!!.homeScreenTextViewCargando.isVisible = false
                    _binding!!.homeScreenTextViewError.isVisible = true
                }
                if (loadState.refresh is LoadState.NotLoading) {
                    _binding!!.homeScreenTextViewCargando.isVisible = false
                }

                _binding!!.appendProgress.isVisible =
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading

            }
        }
    }
}