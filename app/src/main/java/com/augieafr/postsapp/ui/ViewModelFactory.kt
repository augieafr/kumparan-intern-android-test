package com.augieafr.postsapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.augieafr.postsapp.data.source.PostRepository
import com.augieafr.postsapp.injection.Injection
import com.augieafr.postsapp.ui.detailpost.DetailPostViewModel
import com.augieafr.postsapp.ui.detailuser.DetailUserViewModel
import com.augieafr.postsapp.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val postRepository: PostRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                return HomeViewModel(postRepository) as T
            }
            modelClass.isAssignableFrom(DetailPostViewModel::class.java) -> {
                return DetailPostViewModel(postRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }
}