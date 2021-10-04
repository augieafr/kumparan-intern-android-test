package com.augieafr.postsapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity (
    @PrimaryKey
    @NonNull
    val userId: Int,

    @NonNull
    val name: String,

    @NonNull
    val email: String,

    @NonNull
    val address: String,

    @NonNull
    val companyName: String
)