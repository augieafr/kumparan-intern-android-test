package com.augieafr.postsapp.utils

import com.augieafr.postsapp.data.source.local.entity.*
import com.augieafr.postsapp.data.source.remote.response.*
import com.augieafr.postsapp.ui.home.HomePost

/*
    object class that serve as data model mapper.
 */
object DataMapper {
    fun mapPostResponseToEntities(input: List<PostResponse>): List<PostEntity> {
        val postList = ArrayList<PostEntity>()
        input.map {
            val post = PostEntity(
                postId = it.id,
                userId = it.userId,
                title = it.title,
                body = it.body
            )
            postList.add(post)
        }
        return postList
    }

    fun mapUserResponseToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val post = UserEntity(
                userId = it.id,
                name = it.name,
                email = it.email,
                address = it.address.city,
                companyName = it.company.name
            )
            userList.add(post)
        }
        return userList
    }

    fun mapUserResponseToEntities(input: UserResponse): UserEntity {
        return UserEntity(
            userId = input.id,
            name = input.name,
            email = input.email,
            address = input.address.city,
            companyName = input.company.name
        )
    }

    fun mapPhotoResponseToEntities(input: List<AlbumPhotoResponse>): List<PhotoEntity> {
        val photoList = ArrayList<PhotoEntity>()
        input.map {
            val photo = PhotoEntity(
                photoId = it.id,
                albumId = it.albumId,
                title = it.title,
                url = it.url,
                thumbnailUrl = it.thumbnailUrl
            )
            photoList.add(photo)
        }
        return photoList
    }

    fun mapAlbumResponseToEntities(input: List<UserAlbumResponse>): List<AlbumEntity> {
        val albumList = ArrayList<AlbumEntity>()
        input.map {
            val album = AlbumEntity(
                albumId = it.id,
                userId = it.id,
                title = it.title
            )
            albumList.add(album)
        }
        return albumList
    }

    /*
        The app require author name, but there is no author name nor 'userId' in api response.
        There is a 'name' data in response, but it doesn't seem like a person name. So in this case,
        the author name is from 'email' data.
     */
    fun mapCommentResponseToEntities(input: List<CommentResponse>): List<CommentEntity> {
        val commentList = ArrayList<CommentEntity>()
        input.map {
            val comment = CommentEntity(
                commentId = it.id,
                postId = it.id,
                name = getNameFromEmail(it.email),
                body = it.body
            )
            commentList.add(comment)
        }
        return commentList
    }

    fun mapPostEntityToHomePost(input: List<PostEntity>): List<HomePost> {
        val homePostList = ArrayList<HomePost>()
        input.map {
            val homePost = HomePost(
                postId = it.postId,
                userId = it.userId,
                title = it.title,
                userName = "username",
                company = "company",
                body = it.body
            )
            homePostList.add(homePost)
        }
        return homePostList
    }

    private fun getNameFromEmail(input: String): String {
        var name = input.replaceAfter("@", " ")
        name = name.replace(Regex("""[._@,-]"""), " ")
        return name
    }
}