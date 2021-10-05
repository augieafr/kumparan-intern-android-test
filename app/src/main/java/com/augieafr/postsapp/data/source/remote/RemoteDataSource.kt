package com.augieafr.postsapp.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.augieafr.postsapp.data.source.remote.network.ApiResponse
import com.augieafr.postsapp.data.source.remote.network.ApiService
import com.augieafr.postsapp.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val api: ApiService) {

    fun getAllPosts(): LiveData<ApiResponse<List<PostResponse>>> {
        val postResponseList = MutableLiveData<ApiResponse<List<PostResponse>>>()
        api.getAllPosts().enqueue(object : Callback<List<PostResponse>> {
            override fun onResponse(
                call: Call<List<PostResponse>>,
                response: Response<List<PostResponse>>
            ) {
                response.body()?.let {
                    if (it.isNullOrEmpty()) {
                        postResponseList.value = ApiResponse.Empty
                    } else {
                        postResponseList.value =
                            ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                postResponseList.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })

        return postResponseList
    }

    fun getAllUser(): LiveData<ApiResponse<List<UserResponse>>> {
        val userResponseList = MutableLiveData<ApiResponse<List<UserResponse>>>()
        api.getAllUsers().enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                response.body().let {
                    if (it.isNullOrEmpty()) {
                        userResponseList.value = ApiResponse.Empty
                    } else {
                        userResponseList.value = ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                userResponseList.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return userResponseList
    }

    fun getUser(userId: Int): LiveData<ApiResponse<UserResponse>> {
        val userResponse = MutableLiveData<ApiResponse<UserResponse>>()
        api.getUser(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                response.body().let {
                    if (it == null) {
                        userResponse.value = ApiResponse.Empty
                    } else {
                        userResponse.value =
                            ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                userResponse.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return userResponse
    }

    fun getPostComment(postId: Int): LiveData<ApiResponse<List<CommentResponse>>> {
        val commentResponseList = MutableLiveData<ApiResponse<List<CommentResponse>>>()
        api.getPostComments(postId).enqueue(object : Callback<List<CommentResponse>> {
            override fun onResponse(
                call: Call<List<CommentResponse>>,
                response: Response<List<CommentResponse>>
            ) {
                response.body().let {
                    if (it.isNullOrEmpty()) {
                        commentResponseList.value = ApiResponse.Empty
                    } else {
                        commentResponseList.value = ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<CommentResponse>>, t: Throwable) {
                commentResponseList.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return commentResponseList
    }

    fun getUserAlbum(userId: Int): LiveData<ApiResponse<List<UserAlbumResponse>>> {
        val userAlbumList = MutableLiveData<ApiResponse<List<UserAlbumResponse>>>()
        api.getUserAlbum(userId).enqueue(object : Callback<List<UserAlbumResponse>> {
            override fun onResponse(
                call: Call<List<UserAlbumResponse>>,
                response: Response<List<UserAlbumResponse>>
            ) {
                response.body()?.let {
                    if (it.isNullOrEmpty()) {
                        userAlbumList.value = ApiResponse.Empty
                    } else {
                        userAlbumList.value = ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserAlbumResponse>>, t: Throwable) {
                userAlbumList.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return userAlbumList
    }

    fun getAllAlbumPhoto(): LiveData<ApiResponse<List<AlbumPhotoResponse>>> {
        val albumPhotoList = MutableLiveData<ApiResponse<List<AlbumPhotoResponse>>>()
        api.getAllAlbumPhoto().enqueue(object : Callback<List<AlbumPhotoResponse>> {
            override fun onResponse(
                call: Call<List<AlbumPhotoResponse>>,
                response: Response<List<AlbumPhotoResponse>>
            ) {
                response.body().let {
                    if (it.isNullOrEmpty()) {
                        albumPhotoList.value = ApiResponse.Empty
                    } else {
                        albumPhotoList.value = ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<AlbumPhotoResponse>>, t: Throwable) {
                albumPhotoList.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return albumPhotoList
    }

    fun getAlbumPhotoById(id: Int): LiveData<ApiResponse<AlbumPhotoResponse>> {
        val albumPhotoList = MutableLiveData<ApiResponse<AlbumPhotoResponse>>()
        api.getAlbumPhotosById(id).enqueue(object : Callback<AlbumPhotoResponse> {
            override fun onResponse(
                call: Call<AlbumPhotoResponse>,
                response: Response<AlbumPhotoResponse>
            ) {
                response.body().let {
                    if (it == null) {
                        albumPhotoList.value = ApiResponse.Empty
                    } else {
                        albumPhotoList.value = ApiResponse.Success(it)
                    }
                }
            }

            override fun onFailure(call: Call<AlbumPhotoResponse>, t: Throwable) {
                albumPhotoList.value = ApiResponse.Error("Network connection error")
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
        return albumPhotoList
    }

    companion object {
        private const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(api: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(api)
            }
    }
}