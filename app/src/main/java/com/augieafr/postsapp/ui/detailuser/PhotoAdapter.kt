package com.augieafr.postsapp.ui.detailuser

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augieafr.postsapp.data.source.local.entity.PhotoEntity
import com.augieafr.postsapp.databinding.PhotoItemsBinding
import com.augieafr.postsapp.ui.detailphoto.DetailPhotoActivity
import com.squareup.picasso.Picasso

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val listPhoto = ArrayList<PhotoEntity>()

    fun setData(data: List<PhotoEntity>?) {
        if (data == null) return
        listPhoto.clear()
        listPhoto.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = PhotoItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(listPhoto[position])
    }

    override fun getItemCount(): Int = listPhoto.size

    inner class PhotoViewHolder(private val binding: PhotoItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PhotoEntity) {
            with(binding) {
                Picasso.get().load(data.thumbnailUrl).into(ivThumbnail)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailPhotoActivity::class.java)
                intent.putExtra(DetailPhotoActivity.EXTRA_NAME, data.title)
                intent.putExtra(DetailPhotoActivity.EXTRA_URL, data.url)
                itemView.context.startActivity(intent)
            }
        }
    }
}