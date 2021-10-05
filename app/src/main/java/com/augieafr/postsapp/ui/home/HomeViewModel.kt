package com.augieafr.postsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augieafr.postsapp.data.source.IPostRepository


class HomeViewModel(val postRepository: IPostRepository) : ViewModel() {
    val getAllPost = postRepository.getAllPosts()
    val isLoading = MutableLiveData(false)

}
