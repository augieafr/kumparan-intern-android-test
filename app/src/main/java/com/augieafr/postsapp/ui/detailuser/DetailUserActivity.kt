package com.augieafr.postsapp.ui.detailuser

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.augieafr.postsapp.R
import com.augieafr.postsapp.data.source.Resource
import com.augieafr.postsapp.databinding.ActivityDetailUserBinding
import com.augieafr.postsapp.ui.ViewModelFactory
import com.augieafr.postsapp.utils.ToastUtil.makeToast

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val adapter = AlbumAdapter()

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        ).get(DetailUserViewModel::class.java)

        val intentData = intent.getIntExtra(EXTRA_USER_ID, 0)

        viewModel.getUserById(intentData).observe(this) { status ->
            when (status) {
                is Resource.Error -> makeToast(this, "${status.message}")
                is Resource.Loading -> viewModel.isLoading.postValue(true)
                is Resource.Success -> {
                    if (status.data != null) {
                        with(binding) {
                            tvUserName.text = status.data.name
                            tvUserAddress.text = status.data.address
                            tvUserCompany.text = status.data.companyName
                            tvUserEmail.text = status.data.email
                            tvAlbum.text = getString(R.string.user_album, status.data.name)
                        }
                    }
                }
            }
        }


        // nested observer like in HomeActivity
        viewModel.getAlbumById(intentData).observe(this) { status ->
            when (status) {
                is Resource.Error -> makeToast(this, "${status.message}")
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (status.data != null){
                        viewModel.getAllPhoto().observe(this){ listPhoto ->
                            listPhoto.data?.let { viewModel.setAlbum(status.data, it) }
                        }
                    }
                }
            }
        }

        viewModel.album.observe(this){
            adapter.setData(it)
            with(binding.rvAlbum){
                layoutManager = LinearLayoutManager(this@DetailUserActivity)
                this.adapter = adapter
                setHasFixedSize(true)
            }
        }
    }

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }
}