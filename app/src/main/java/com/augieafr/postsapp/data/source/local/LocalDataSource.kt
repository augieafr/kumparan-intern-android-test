package com.augieafr.postsapp.data.source.local

import com.augieafr.postsapp.data.source.local.entity.*
import com.augieafr.postsapp.data.source.local.room.PostDao

class LocalDataSource(private val postDao: PostDao) {

    fun getAllPost() = postDao.getAllPost()

    fun getAllUser() = postDao.getAllUser()

    fun getUserById(id: Int) = postDao.getUserByUserId(id)

    fun getAlbumByUserId(userId: Int) = postDao.getAlbumByUserId(userId)

    fun getCommentByPostId(postId: Int) = postDao.getCommentByPostId(postId)

    fun getPhotoByAlbumId(albumId: Int) = postDao.getCommentByPostId(albumId)

    fun insertPost(listPost: List<PostEntity>) = postDao.insertPost(listPost)

    fun insertUser(user: UserEntity) = postDao.insertUser(user)

    fun insertUser(listUser: List<UserEntity>) = postDao.insertUser(listUser)

    fun insertAlbum(listAlbum: List<AlbumEntity>) = postDao.insertAlbum(listAlbum)

    fun insertComment(listComment: List<CommentEntity>) = postDao.insertComment(listComment)

    fun insertPhoto(listPhoto: List<PhotoEntity>) = postDao.insertPhoto(listPhoto)

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: PostDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(tourismDao)
            }
    }
}