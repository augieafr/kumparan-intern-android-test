package com.augieafr.postsapp.ui.detailpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augieafr.postsapp.data.source.IPostRepository

class DetailPostViewModel(private val repository: IPostRepository) : ViewModel() {
    val isLoading = MutableLiveData(false)
    fun getComment(postId: Int) = repository.getComment(postId)
}