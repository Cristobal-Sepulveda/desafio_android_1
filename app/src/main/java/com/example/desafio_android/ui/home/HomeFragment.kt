package com.example.desafio_android.ui.home

import android.os.Bundle
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
import org.koin.android.ext.android.inject
import com.example.desafio_android.ui.home.HomeFragmentDirections
import com.example.desafio_android.utils.asParcelable

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val _viewModel: HomeViewModel by inject()
    private lateinit var adapter: HomeRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        adapter = HomeRecyclerViewAdapter(HomeRecyclerViewAdapter.OnClickListener{
            _viewModel.displayGitHubJavaRepositoryDetails(it)
        })

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = this
        _binding!!.recyclerviewVistaGeneralListadoDeEmergencias.adapter = adapter


        _viewModel.listaEnPantalla.observe(viewLifecycleOwner) {
            it.let { adapter.submitList(it as MutableList<GitHubJavaRepository>) }
        }

        _viewModel.navigateToSelectedGitHubJavaRepository.observe(viewLifecycleOwner) {
            if (null != it){
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationDetails(it.asParcelable()))
                _viewModel.displayGitHubJavaRepositoryDetailsCompleted()
            }
        }

        obtenerJavaRepositoriesFromGitHub()
        return _binding!!.root
    }


    private fun obtenerJavaRepositoriesFromGitHub(){
        lifecycleScope.launch(Dispatchers.IO) {
            val task = _viewModel.obtenerJavaRepositoriesFromGitHub()
            if (!task.first) showToastInMainThread(requireContext(), task.second)
        }
    }
}