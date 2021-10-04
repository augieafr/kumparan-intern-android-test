package com.augieafr.postsapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment_entity")
data class CommentEntity(
    @PrimaryKey
    @NonNull
    val commentId: Int,

    @NonNull
    val postId: Int,

    @NonNull
    val name: String,

    @NonNull
    val body: String
)
