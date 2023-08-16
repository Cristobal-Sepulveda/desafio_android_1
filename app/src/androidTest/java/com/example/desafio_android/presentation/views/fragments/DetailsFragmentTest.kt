package com.example.desafio_android.presentation.views.fragments

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.desafio_android.data.dataclasses.parcelables.ParcelableGHJavaRepository
import org.junit.Test
import org.junit.runner.RunWith
import com.example.desafio_android.R


@RunWith(AndroidJUnit4::class)
//los instrumental son anotados con esta, los unit son small pero esa es por defecto
//los end-to-end son large
@MediumTest
class DetailsFragmentTest{

    @Test
    fun activeDetailFragment_DisplayedInUi(){
        //Given - bundle
        val parcelableGitHubJavaRepository = ParcelableGHJavaRepository(
            1,
            "name",
            "full_name",
            "owner",
            "description",
            1,
            1
        )

        val fragmentArgs = bundleOf("parcelableGitHubJavaRepository" to parcelableGitHubJavaRepository)
        launchFragmentInContainer<DetailsFragment>(fragmentArgs)w
        // Launch the fragment in the test activity
        val scenario = launchFragmentInContainer<DetailsFragment>(
            fragmentArgs,
            R.style.Theme_Desafioandroid,
            factory = object : FragmentFactory() {
                override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                    return if (className == DetailsFragment::class.java.name) {
                        // Return your fragment instance here
                        DetailsFragment()
                    } else {
                        super.instantiate(classLoader, className)
                    }
                }
            }
        )
        Thread.sleep(2000)
    }
}
