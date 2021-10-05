package com.augieafr.postsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augieafr.postsapp.data.source.IPostRepository
import com.augieafr.postsapp.data.source.local.entity.PostEntity
import com.augieafr.postsapp.data.source.local.entity.UserEntity
import com.augieafr.postsapp.utils.DataMapper


class HomeViewModel(postRepository: IPostRepository) : ViewModel() {

    val getAllPost = postRepository.getAllPosts()
    val getAllUser = postRepository.getAllUser()
    val isLoading = MutableLiveData(false)

    private val _homePost = MutableLiveData<List<HomePost>>()
    val homePost : LiveData<List<HomePost>> = _homePost

    /*
        What this function do is take all post and user data, then the user data is transformed into
        map with user id as its key and a list of user name and user company name as its value.
        Next it will loop the list of Post, match its userId to user map key. With this way we can
        get two data from different api. It is not proper way and will get really bad if the
        user data is huge (like what will happen in list of photo in detail user activity).
     */
    fun setHomePost(listPost: List<PostEntity>, listUser: List<UserEntity>) {
        val userMap = listUser.associateBy({it.userId}, { listOf(it.name, it.companyName)})

        val listHomePost = DataMapper.mapPostEntityToHomePost(listPost, userMap)
        _homePost.value = listHomePost
    }

}
