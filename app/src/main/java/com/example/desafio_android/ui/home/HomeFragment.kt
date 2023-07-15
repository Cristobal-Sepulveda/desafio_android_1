package com.example.desafio_android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.databinding.FragmentHomeBinding
import com.example.desafio_android.utils.showToastInMainThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.desafio_android.utils.asParcelable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    /*private val _viewModel: HomeViewModel by inject()*/
    private val _viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = HomeRecyclerViewAdapter(_viewModel)
        
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner
        _binding!!.homeScreenRecyclerViewListadoDeRepositories.adapter = adapter

        _viewModel.listToDisplay.observe(viewLifecycleOwner) {
            adapter.submitList(it as MutableList<GitHubJavaRepository>)
        }

        _viewModel.shouldINavigate.observe(viewLifecycleOwner) {
            it?.let{
                findNavController().navigate(
                    HomeFragmentDirections
                        .actionNavigationHomeToNavigationDetails(it.asParcelable())
                )
                _viewModel.navigationCompleted()
            }
        }

        obtenerJavaRepositoriesFromGitHub()
        return _binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Home Fragment", "onDestroy")
    }


    private fun obtenerJavaRepositoriesFromGitHub(){
        lifecycleScope.launch(Dispatchers.IO) {
            val task = _viewModel.obtenerJavaRepositoriesFromGitHub()
            if (!task.first) showToastInMainThread(requireContext(), task.second)
        }
    }
}