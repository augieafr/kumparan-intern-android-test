package com.augieafr.postsapp.data.source.remote.network

import com.augieafr.postsapp.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getAllPosts() : Call<List<PostResponseItem>>

    @GET("users")
    fun getAllUsers() : Call<List<UserResponseItem>>

    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ) : Call<UserResponseItem>

    @GET("posts/{postId}/comments")
    fun getPostComments(
        @Path("postId") postId: Int
    ) : Call<List<CommentResponseItem>>

    @GET("albums?userId={userId}")
    fun getUserAlbum(
        @Path("userId") userId: Int
    ) : Call<List<UserAlbumResponseItem>>

    @GET("photos?albumId={albumId}")
    fun getAlbumPhoto(
        @Path("albumId") albumid: Int
    ) : Call<List<AlbumPhotoResponseItem>>
}