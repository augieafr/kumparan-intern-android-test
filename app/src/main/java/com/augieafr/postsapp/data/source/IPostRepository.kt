package com.augieafr.postsapp.data.source

import androidx.lifecycle.LiveData
import com.augieafr.postsapp.data.source.local.entity.*

interface IPostRepository {
    fun getAllPosts(): LiveData<Resource<List<PostEntity>>>
    fun getAllUser(): LiveData<Resource<List<UserEntity>>>
    fun getUser(id: Int): LiveData<Resource<UserEntity>>
    fun getAlbum(userId: Int): LiveData<Resource<List<AlbumEntity>>>
    fun getPhoto(albumId: Int): LiveData<Resource<List<PhotoEntity>>>
    fun getComment(postId: Int): LiveData<Resource<List<CommentEntity>>>
}