package com.augieafr.postsapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserAlbumResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("userId")
	val userId: Int
)
