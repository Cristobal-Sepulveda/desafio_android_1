package com.example.desafio_android.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.desafio_android.utils.Constants.CloudRequestStatus



@BindingAdapter("cloudRequestStatusCircularProgress")
fun bindStatusErrorCircularProgress(progressBar: ProgressBar, status: LiveData<CloudRequestStatus>?) {
    Log.d("BindingAdapter", "bindStatusErrorCircularProgress: ${status?.value}")
    when (status?.value) {
        CloudRequestStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        CloudRequestStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        CloudRequestStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
        CloudRequestStatus.DONE_WITH_NO_DATA -> {
            progressBar.visibility = View.GONE
        }
        else -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("cloudRequestStatusImage")
fun bindStatusErrorImage(imageView: ImageView, status: LiveData<CloudRequestStatus>?) {
    Log.d("BindingAdapter", "bindStatusErrorImage: ${status?.value}")
    when (status?.value) {
        CloudRequestStatus.LOADING -> {
            imageView.visibility = View.GONE
        }
        CloudRequestStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
        }
        CloudRequestStatus.DONE -> {
            imageView.visibility = View.GONE
        }
        CloudRequestStatus.DONE_WITH_NO_DATA -> {
            imageView.visibility = View.GONE
        }
        else -> {
            imageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("CambiarBooleanASiONo")
fun bindCambiarBooleanASiONo(textView: TextView, boolean: Boolean){
    when(boolean){
        true -> {
            textView.text = "Sí"
        }
        false -> {
            textView.text = "No"
        }
    }
}

@BindingAdapter("boolean1", "boolean2")
fun bindCambiarBooleanAAprobadaORechazada(textView: TextView, boolean1: Boolean, boolean2: Boolean){
    if(boolean1){
        if(boolean2) {
            textView.text = "Aprobada"
        }else{
            textView.text = "Rechazada"
        }
    }else{
        textView.text = "Sin gestionar"
    }
}

@BindingAdapter("cambiarLongAString")
fun bindCambiarLongAString(textView: TextView, long: Long){
    textView.text = long.toString()
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String){
    Log.e("imageUrl", "bindImage")
    val imgUri = imgUrl.toUri().buildUpon()?.scheme("https")?.build()
    Glide.with(imgView.context)
        .load(imgUri)
        .into(imgView)
}