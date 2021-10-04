package com.augieafr.postsapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_entity")
data class PostEntity(
    @PrimaryKey
    @NonNull
    val postId: Int,

    @NonNull
    val userId: Int,

    @NonNull
    val title: String,

    @NonNull
    val body: String
)
