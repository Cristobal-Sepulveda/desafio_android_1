package com.example.desafio_android.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast



fun showToastInMainThread(context: Context, message: Int){
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}




