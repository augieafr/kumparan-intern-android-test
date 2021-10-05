package com.augieafr.postsapp.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}