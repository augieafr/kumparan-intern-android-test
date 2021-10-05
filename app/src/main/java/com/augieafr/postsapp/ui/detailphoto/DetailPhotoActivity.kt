package com.augieafr.postsapp.ui.detailphoto

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.augieafr.postsapp.R
import com.squareup.picasso.Picasso

class DetailPhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_photo)

        val name = intent.getStringExtra(EXTRA_NAME)
        findViewById<TextView>(R.id.tv_title).text = name

        val url = intent.getStringExtra(EXTRA_URL)
        val image = findViewById<ImageView>(R.id.iv_photo_full)
        Picasso.get().load(url).into(image)
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_URL = "extra_url"
    }
}