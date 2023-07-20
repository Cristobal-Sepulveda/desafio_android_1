package com.example.desafio_android.ui.details

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.desafio_android.R
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryPullRequestDTO
import com.example.desafio_android.databinding.FragmentDetailsBinding
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val _viewModel: DetailsViewModel by viewModel()
    private lateinit var adapter: DetailsRecyclerViewAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle =  DetailsFragmentArgs
            .fromBundle(requireArguments())
            .parcelableGitHubJavaRepository

        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).title = bundle.name

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner

        adapter = DetailsRecyclerViewAdapter(DetailsRecyclerViewAdapter.OnClickListener{
            openPullRequestInChrome(it)
        })

        _binding!!.detailsScreenRecyclerViewListOfRepositoryPullRequests.adapter = adapter


        _viewModel.listToDisplay.observe(viewLifecycleOwner) {
            it.let { adapter.submitList(it) }
        }

        _viewModel.pullRequestsOpenedAndClosed.observe(viewLifecycleOwner){
            _binding!!.detailsTextViewDivider.visibility = View.VISIBLE
            _binding!!.detailsTextViewOpened.text = "${it.first} opened"
            _binding!!.detailsTextViewClosed.text = "${it.second} closed"
        }

        _viewModel.fullName = bundle.full_name
        _viewModel.gettingRepositoryPullRequests()

        return _binding!!.root
    }

    private fun openPullRequestInChrome(it: GHJavaRepositoryPullRequestDO) {
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

