package com.augieafr.postsapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_entity")
data class PhotoEntity(
    @PrimaryKey
    @NonNull
    val photoId: Int,

    @NonNull
    val albumId: Int,

    @NonNull
    val title: String,

    @NonNull
    val url: String,

    @NonNull
    val thumbnailUrl: String
)
