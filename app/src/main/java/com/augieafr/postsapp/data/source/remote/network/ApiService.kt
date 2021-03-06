package com.augieafr.postsapp.data.source.remote.network

import com.augieafr.postsapp.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    fun getAllPosts(): Call<List<PostResponse>>

    @GET("users")
    fun getAllUsers(): Call<List<UserResponse>>

    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ): Call<UserResponse>

    @GET("posts/{postId}/comments")
    fun getPostComments(
        @Path("postId") postId: Int
    ): Call<List<CommentResponse>>

    @GET("albums")
    fun getUserAlbum(
        @Query("userId") userId: Int
    ): Call<List<UserAlbumResponse>>

    @GET("photos")
    fun getAllAlbumPhoto(): Call<List<AlbumPhotoResponse>>

    @GET("photos/id={photosId)")
    fun getAlbumPhotosById(
        @Path("photosId") photosId: Int
    ): Call<AlbumPhotoResponse>
}