package com.example.desafio_android.ui.details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.desafio_android.R
import com.example.desafio_android.data.dto.GitHubJavaRepository
import com.example.desafio_android.data.dto.ParcelableGitHubJavaRepository
import com.example.desafio_android.data.dto.RepositoryPullRequest
import com.example.desafio_android.databinding.FragmentDetailsBinding
import com.example.desafio_android.ui.home.HomeRecyclerViewAdapter
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val _viewModel: DetailsViewModel by viewModel()
    private lateinit var adapter: DetailsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner

        val bundle =  DetailsFragmentArgs
            .fromBundle(requireArguments())
            .parcelableGitHubJavaRepository

        adapter = DetailsRecyclerViewAdapter(DetailsRecyclerViewAdapter.OnClickListener{
            openPullRequestInChrome(it)
        })

        _binding!!.detailsScreenRecyclerViewListOfRepositoryPullRequests.adapter = adapter
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).title = bundle.name

        _viewModel.listInScreen.observe(viewLifecycleOwner) {
            it.let { adapter.submitList(it as MutableList<RepositoryPullRequest>) }
        }

        getScreenData(bundle)

        return _binding!!.root
    }

    private fun getScreenData(bundle: ParcelableGitHubJavaRepository) {
        lifecycleScope.launch(Dispatchers.IO){
            val fullName = bundle.full_name!!
            _viewModel.fullName = fullName
            _viewModel.obtenerJavaRepositoryFromGitHub(fullName)
        }
    }

    private fun openPullRequestInChrome(it: RepositoryPullRequest) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.apply {
            data = Uri.parse(it.html_url)
            setPackage("com.android.chrome")
        }
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {

        }
    }
}

