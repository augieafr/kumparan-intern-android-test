package com.augieafr.postsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.augieafr.postsapp.data.source.Resource
import com.augieafr.postsapp.databinding.ActivityHomeBinding
import com.augieafr.postsapp.ui.ViewModelFactory
import com.augieafr.postsapp.ui.detailpost.DetailPostActivity
import com.augieafr.postsapp.utils.ToastUtil.makeToast

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

        viewModel.getAllPost.observe(this) { status ->
            when (status) {
                is Resource.Error -> makeToast(this, "${status.message}")
                is Resource.Loading -> viewModel.isLoading.postValue(true)
                is Resource.Success -> {
                    if (status.data.isNullOrEmpty()) {
                        makeToast(this, "There are no post yet")
                        viewModel.isLoading.postValue(false)
                    } else {
                        viewModel.getAllUser.observe(this){ listUser ->
                            when (listUser){
                                is Resource.Error -> makeToast(this, "${status.message}")
                                is Resource.Loading -> viewModel.isLoading.postValue(true)
                                is Resource.Success -> {
                                    viewModel.isLoading.postValue(false)
                                    listUser.data?.let { viewModel.setHomePost(status.data, it) }
                                }
                            }
                        }
                    }
                }
            }
        }

        viewModel.homePost.observe(this) { listHomePost ->
            adapter.setData(listHomePost)
            adapter.onItemClick = {
                // intent to detail page screen
                val intent = Intent(this@HomeActivity, DetailPostActivity::class.java)
                intent.putExtra(DetailPostActivity.EXTRA_POST, it)
                startActivity(intent)
            }

            with(binding.rvPosts) {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
                setHasFixedSize(true)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            loadingState(isLoading)
        }
    }

    private fun loadingState(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvPosts.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvPosts.visibility = View.VISIBLE
        }
    }

}