package com.augieafr.postsapp.ui.detailpost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.augieafr.postsapp.data.source.Resource
import com.augieafr.postsapp.databinding.ActivityDetailPostBinding
import com.augieafr.postsapp.ui.ViewModelFactory
import com.augieafr.postsapp.ui.detailuser.DetailUserActivity
import com.augieafr.postsapp.ui.home.HomePost
import com.augieafr.postsapp.utils.ToastUtil.makeToast

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private lateinit var viewModel: DetailPostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val adapter = CommentAdapter()
        val intentData = intent.getParcelableExtra<HomePost>(EXTRA_POST)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        ).get(DetailPostViewModel::class.java)

        if (intentData != null) {

            with(binding){
                tvDetailPostTitle.text = intentData.title
                tvDetailPostBody.text = intentData.body
                tvDetailPostUsername.text = intentData.userName
                tvDetailPostUsername.setOnClickListener {
                    // intent to detail user
                    val intent = Intent(this@DetailPostActivity, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_USER_ID, intentData.userId)
                    startActivity(intent)
                }
            }


            viewModel.getComment(intentData.postId).observe(this) { status ->
                when (status) {
                    is Resource.Error -> makeToast(this, "${status.message}")
                    is Resource.Loading -> viewModel.isLoading.postValue(true)
                    is Resource.Success -> {
                        viewModel.isLoading.postValue(false)
                        if (status.data.isNullOrEmpty()) {
                        } else {
                            adapter.setData(status.data)
                            with(binding.rvComment) {
                                layoutManager = LinearLayoutManager(context)
                                this.adapter = adapter
                                setHasFixedSize(true)
                            }
                        }
                    }
                }
            }

            viewModel.isLoading.observe(this) {
                loadingState(it)
            }
        }
    }

    private fun loadingState(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvComment.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvComment.visibility = View.VISIBLE
        }
    }

    companion object {
        const val EXTRA_POST = "extra_post"
    }
}