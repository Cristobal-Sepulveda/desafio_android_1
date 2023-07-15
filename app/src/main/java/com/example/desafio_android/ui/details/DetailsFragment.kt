package com.example.desafio_android.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.desafio_android.databinding.FragmentDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailsFragment(): Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val _viewModel: DetailsViewModel by inject()
    // by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val gitHubJavaRepository = DetailsFragmentArgs.fromBundle(requireArguments()).parcelableGitHubJavaRepository
        Log.e("DetailsFragment", "$gitHubJavaRepository")

        gitHubJavaRepository.full_name?.let{
            obtenerJavaRepositoryFromGitHub(it)
        }

        return _binding!!.root
    }

    private fun obtenerJavaRepositoryFromGitHub(fullName: String){
        lifecycleScope.launch(Dispatchers.IO){
            _viewModel.obtenerJavaRepositoryFromGitHub(fullName)
        }
    }
}

// Field a los livedata para que sean publicos o privados en una osla instancia

