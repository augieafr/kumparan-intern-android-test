package com.augieafr.postsapp.data.source.remote.network

import com.augieafr.postsapp.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getAllPosts() : Call<PostResponse>

    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ) : Call<UserResponse>

    @GET("posts/{postId}/comments")
    fun getPostComments(
        @Path("postId") postId: Int
    ) : Call<CommentResponse>

    @GET("albums?userId={userId}")
    fun getUserAlbum(
        @Path("userId") userId: Int
    ) : Call<UserAlbumResponse>

    @GET("photos?albumId={albumId}")
    fun getAlbumPhoto(
        @Path("albumId") albumid: Int
    ) : Call<AlbumPhotoResponse>
}