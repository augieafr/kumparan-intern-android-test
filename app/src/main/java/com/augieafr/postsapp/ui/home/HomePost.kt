package com.augieafr.postsapp.ui.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomePost(
    val postId: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val userName: String,
    val company: String
) : Parcelable
