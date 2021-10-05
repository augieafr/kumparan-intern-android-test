package com.augieafr.postsapp.ui.detailuser

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.augieafr.postsapp.databinding.AlbumItemsBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val listAlbum = ArrayList<Album>()

    fun setData(data: List<Album>?) {
        if (data == null) return
        listAlbum.clear()
        listAlbum.addAll(data)
        notifyDataSetChanged()
    }

    inner class AlbumViewHolder(private val binding: AlbumItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Album) {
            val photoAdapter = PhotoAdapter()
            photoAdapter.setData(data.photo)
            binding.tvTitle.text = data.name
            with(binding.rvPhoto) {
                layoutManager =
                    GridLayoutManager(itemView.context, 3)
                adapter = photoAdapter
                setHasFixedSize(true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = AlbumItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(listAlbum[position])
    }

    override fun getItemCount(): Int = listAlbum.size
}