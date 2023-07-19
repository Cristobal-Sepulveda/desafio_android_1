package com.example.desafio_android.utils

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.desafio_android.R


@BindingAdapter("apiRequestErrorImage")
fun bindApiRequestErrorImage(imageView: ImageView, status: LiveData<Boolean?>) {
    if(status.value == null){
        imageView.visibility = View.VISIBLE
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageURL: String){
    try{
        val imgUri = imageURL.toUri().buildUpon()?.scheme("https")?.build()
        Glide.with(imageView.context)
            .load(imgUri)
            .into(imageView)
    }catch(e:Exception){
        imageView.setImageResource(R.drawable.ic_connection_error_48)
    }

}