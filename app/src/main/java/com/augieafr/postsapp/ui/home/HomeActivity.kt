package com.augieafr.postsapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.augieafr.postsapp.data.source.Resource
import com.augieafr.postsapp.databinding.ActivityHomeBinding
import com.augieafr.postsapp.ui.PostsAdapter
import com.augieafr.postsapp.ui.ViewModelFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostsAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        ).get(HomeViewModel::class.java)

        viewModel.getAllPost.observe(this){ status ->
            when(status){
                is Resource.Error -> makeToast(this, "${status.message}")
                is Resource.Loading -> viewModel.isLoading.postValue(true)
                is Resource.Success -> {
                    // call get user
                    adapter.setData(status.data)
                    adapter.onItemClick = {
                        makeToast(this, "Click: ${it.userId}")
                    }
                    with(binding.rvPosts) {
                        layoutManager = LinearLayoutManager(context)
                        this.adapter = adapter
                        setHasFixedSize(true)
                    }
                    viewModel.isLoading.postValue(false)
                }
            }
        }
        viewModel.isLoading.observe(this){ isLoading ->
            loadingState(isLoading)
        }
    }

    private fun loadingState(isLoading: Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvPosts.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvPosts.visibility = View.VISIBLE
        }
    }
    private fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}