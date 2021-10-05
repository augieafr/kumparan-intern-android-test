package com.augieafr.postsapp.data.source

import androidx.lifecycle.LiveData
import com.augieafr.postsapp.data.source.local.LocalDataSource
import com.augieafr.postsapp.data.source.local.entity.*
import com.augieafr.postsapp.data.source.remote.RemoteDataSource
import com.augieafr.postsapp.data.source.remote.network.ApiResponse
import com.augieafr.postsapp.data.source.remote.response.*
import com.augieafr.postsapp.utils.AppExecutors
import com.augieafr.postsapp.utils.DataMapper

/*
    This repository implement the network bound resource to achieve online-offline app, means the app
    will still show the content even without internet connection IF the user had fetched the content
    before.
    For now, the app will only fetch data if the data didn't exists in local database.
 */
class PostRepository(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
    private val appExecutors: AppExecutors
) : IPostRepository {
    override fun getAllPosts(): LiveData<Resource<List<PostEntity>>> =
        object : NetworkBoundResource<List<PostEntity>, List<PostResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<PostEntity>> =
                local.getAllPost()

            override fun shouldFetch(data: List<PostEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<PostResponse>>> =
                remote.getAllPosts()

            override fun saveCallResult(data: List<PostResponse>) {
                val postList = DataMapper.mapPostResponseToEntities(data)
                local.insertPost(postList)
            }
        }.asLiveData()

    override fun getAllUser(): LiveData<Resource<List<UserEntity>>> =
        object : NetworkBoundResource<List<UserEntity>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<UserEntity>> =
                local.getAllUser()

            override fun shouldFetch(data: List<UserEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remote.getAllUser()

            override fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponseToEntities(data)
                local.insertUser(userList)
            }
        }.asLiveData()

    override fun getUser(id: Int): LiveData<Resource<UserEntity>> =
        object : NetworkBoundResource<UserEntity, UserResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<UserEntity> =
                local.getUserById(id)

            override fun shouldFetch(data: UserEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<UserResponse>> =
                remote.getUser(id)

            override fun saveCallResult(data: UserResponse) {
                val user = DataMapper.mapUserResponseToEntities(data)
                local.insertUser(user)
            }
        }.asLiveData()

    override fun getAlbum(userId: Int): LiveData<Resource<List<AlbumEntity>>> =
        object : NetworkBoundResource<List<AlbumEntity>, List<UserAlbumResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<AlbumEntity>> =
                local.getAlbumByUserId(userId)

            override fun shouldFetch(data: List<AlbumEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UserAlbumResponse>>> =
                remote.getUserAlbum(userId)

            override fun saveCallResult(data: List<UserAlbumResponse>) {
                val albumList = DataMapper.mapAlbumResponseToEntities(data)
                local.insertAlbum(albumList)
            }
        }.asLiveData()

    override fun getPhoto(albumId: Int): LiveData<Resource<List<PhotoEntity>>> =
        object : NetworkBoundResource<List<PhotoEntity>, List<AlbumPhotoResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<PhotoEntity>> =
                local.getPhotoByAlbumId(albumId)

            override fun shouldFetch(data: List<PhotoEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<AlbumPhotoResponse>>> =
                remote.getAlbumPhoto(albumId)

            override fun saveCallResult(data: List<AlbumPhotoResponse>) {
                val listPhoto = DataMapper.mapPhotoResponseToEntities(data)
                local.insertPhoto(listPhoto)
            }
        }.asLiveData()

    override fun getComment(postId: Int): LiveData<Resource<List<CommentEntity>>> =
        object : NetworkBoundResource<List<CommentEntity>, List<CommentResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<CommentEntity>> =
                local.getCommentByPostId(postId)

            override fun shouldFetch(data: List<CommentEntity>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CommentResponse>>> =
                remote.getPostComment(postId)

            override fun saveCallResult(data: List<CommentResponse>) {
                val listComment = DataMapper.mapCommentResponseToEntities(data)
                local.insertComment(listComment)
            }
        }.asLiveData()
}