package com.example.desafio_android.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.desafio_android.databinding.FragmentHomeBinding
import com.example.desafio_android.utils.DiffCallBackProvider
import com.example.desafio_android.utils.asParcelable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val _viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: GHJavaRepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = GHJavaRepositoryAdapter(_viewModel, DiffCallBackProvider())
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.homeScreenRecyclerViewListadoDeRepositories.adapter = adapter

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
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect { loadState ->
/*                    Log.e("Home Fragment", "charging new data")
                    Log.e("cantidad de datos ", adapter.itemCount.toString())
                    for (item in adapter.snapshot().items) {
                        Log.e("items", "${item}")
                    }*/

                    _binding!!.appendProgress.isVisible =
                        loadState.source.append is LoadState.Loading
                    _binding!!.homeCircularProgressIndicator.isVisible =
                        loadState.source.refresh is LoadState.Loading && !_viewModel.isFirstLoadingDone
                }
            }
        }

        _viewModel.getJavaRepositories()

        return _binding!!.root
    }

}