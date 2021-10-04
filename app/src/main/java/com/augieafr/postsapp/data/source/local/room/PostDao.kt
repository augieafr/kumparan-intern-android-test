package com.augieafr.postsapp.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.augieafr.postsapp.data.source.local.entity.*

@Dao
interface PostDao {

    @Query("SELECT * FROM post_entity")
    fun getAllPost(): LiveData<List<PostEntity>>

    @Query("SELECT * FROM user_entity")
    fun getAllUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_entity WHERE userId = :id")
    fun getUserByUserId(id: Int): LiveData<UserEntity>

    @Query("SELECT * FROM album_entity WHERE userId = :userId")
    fun getAlbumByUserId(userId: Int): LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM comment_entity WHERE postId = :postId")
    fun getCommentByPostId(postId: Int): LiveData<List<CommentEntity>>

    @Query("SELECT * FROM photo_entity WHERE albumId = albumId")
    fun getPhotoByAlbumId(albumId: Int): LiveData<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(listPost: List<PostEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(listUser: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(listAlbum: List<AlbumEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(listComment: List<CommentEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(listPhoto: List<PhotoEntity>)
}