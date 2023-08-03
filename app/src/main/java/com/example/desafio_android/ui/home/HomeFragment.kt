package com.example.desafio_android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.desafio_android.data.dataclasses.domainObjects.asParcelable
import com.example.desafio_android.databinding.FragmentHomeBinding
import com.example.desafio_android.utils.DiffCallBackProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val _viewModel: HomeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner

        val adapter = GHJavaRepositoryAdapter(_viewModel, DiffCallBackProvider())
        _binding!!.homeScreenRecyclerViewListadoDeRepositories.adapter = adapter

        _viewModel.getJavaRepositories()

        _viewModel.listToDisplay.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO){
                adapter.submitData(it)
            }
        }

        _viewModel.shouldINavigate.observe(viewLifecycleOwner) { gitHubJavaRepository ->
            gitHubJavaRepository?.let {
                findNavController().navigate(
                    HomeFragmentDirections
                        .actionNavigationHomeToNavigationDetails(it.asParcelable())
                )
                _viewModel.navigationCompleted()
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                Log.e("Loading", adapter.itemCount.toString())
                _binding!!.appendProgress.isVisible =
                    loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading

                if(loadState.refresh is LoadState.NotLoading){
                    _binding!!.homeScreenTextViewCargando.isVisible = false
                }
            }
        }

        return _binding!!.root
    }

}