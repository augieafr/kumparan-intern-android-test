package com.augieafr.postsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augieafr.postsapp.data.source.local.entity.PostEntity
import com.augieafr.postsapp.databinding.PostItemsBinding
import com.augieafr.postsapp.ui.home.HomePost

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var onItemClick: ((PostEntity) -> Unit)? = null
    private var listPost = ArrayList<PostEntity>()

    fun setData(data: List<PostEntity>?) {
        if (data == null) return
        listPost.clear()
        listPost.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = listPost[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = listPost.size

    inner class PostViewHolder(private val binding: PostItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(posts: PostEntity) {
            with(binding) {
                tvPostBody.text = posts.body
                tvPostTitle.text = posts.title
                tvPostUserName.text = "username"
                tvPostUserCompany.text = "company"

                itemView.setOnClickListener {
                    onItemClick?.invoke(posts)
                }
            }
        }
    }
}