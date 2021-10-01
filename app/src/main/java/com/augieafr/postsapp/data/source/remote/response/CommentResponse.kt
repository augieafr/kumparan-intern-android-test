package com.augieafr.postsapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("CommentResponse")
	val commentResponse: List<CommentResponseItem>
)

data class CommentResponseItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("postId")
	val postId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("email")
	val email: String
)