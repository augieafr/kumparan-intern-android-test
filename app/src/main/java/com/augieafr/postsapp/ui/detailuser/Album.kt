package com.augieafr.postsapp.ui.detailuser

import com.augieafr.postsapp.data.source.local.entity.PhotoEntity

data class Album(
    val name: String,
    val photo: List<PhotoEntity>
)
