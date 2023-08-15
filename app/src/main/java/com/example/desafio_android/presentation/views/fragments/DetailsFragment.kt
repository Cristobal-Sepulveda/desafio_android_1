package com.example.desafio_android.presentation.views.fragments

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.desafio_android.R
import com.example.desafio_android.domain.GHJavaRepositoryPullRequestDO
import com.example.desafio_android.databinding.FragmentDetailsBinding
import com.example.desafio_android.presentation.adapters.ghpullrequest.GHPullRequestAdapter
import com.example.desafio_android.presentation.viewmodels.DetailsViewModel
import com.example.desafio_android.utils.DiffCallBackProvider
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment: Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val _viewModel: DetailsViewModel by viewModel()
    private lateinit var adapter: GHPullRequestAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle =  DetailsFragmentArgs.fromBundle(
            requireArguments()
        ).parcelableGitHubJavaRepository

        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar).title = bundle.name

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        _binding!!.viewModel = _viewModel
        _binding!!.lifecycleOwner = viewLifecycleOwner

        adapter = GHPullRequestAdapter(
            GHPullRequestAdapter.OnClickListener{
                openPullRequestLink(it)
            },
            DiffCallBackProvider()
        )

        _binding!!.detailsScreenRecyclerViewListOfRepositoryPullRequests.adapter = adapter


        _viewModel.listToDisplay.observe(viewLifecycleOwner) {
            it.let {
                if(it.isEmpty()) _binding!!.detailsScreenTextViewNoHayPullRequests.visibility = View.VISIBLE
                adapter.submitList(it)
            }
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

    private fun openPullRequestLink(it: GHJavaRepositoryPullRequestDO) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.html_url))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "Error: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

