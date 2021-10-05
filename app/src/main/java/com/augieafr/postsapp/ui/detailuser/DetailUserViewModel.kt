package com.augieafr.postsapp.ui.detailuser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augieafr.postsapp.data.source.IPostRepository
import com.augieafr.postsapp.data.source.local.entity.AlbumEntity
import com.augieafr.postsapp.data.source.local.entity.PhotoEntity
import com.augieafr.postsapp.utils.DataMapper
import java.util.stream.Collectors

class DetailUserViewModel(private val repository: IPostRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val album = MutableLiveData<List<Album>>()

    fun getUserById(userId: Int) = repository.getUser(userId)
    fun getAlbumById(userId: Int) = repository.getAlbum(userId)
    fun getAllPhoto() = repository.getAllPhoto()

    fun setAlbum(listAlbumEntity: List<AlbumEntity>, listPhoto: List<PhotoEntity>) {

        // for future myself: https://stackoverflow.com/questions/3019376/shortcut-for-adding-to-list-in-a-hashmap
        val mapPhoto = listPhoto.stream().collect(Collectors.groupingBy(PhotoEntity::albumId))

        val listAlbum = DataMapper.mapAlbumEntityToAlbum(listAlbumEntity, mapPhoto)
        album.postValue(listAlbum)
    }
}