package com.augieafr.postsapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_entity")
data class AlbumEntity(
    @PrimaryKey
    @NonNull
    val albumId: Int,

    @NonNull
    val userId: Int,

    @NonNull
    val title: String
)
