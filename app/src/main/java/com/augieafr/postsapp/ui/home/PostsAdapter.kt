package com.augieafr.postsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augieafr.postsapp.databinding.PostItemsBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    var onItemClick: ((HomePost) -> Unit)? = null
    private var listPost = ArrayList<HomePost>()

    fun setData(data: List<HomePost>?) {
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
        fun bind(posts: HomePost) {
            with(binding) {
                tvPostBody.text = posts.body
                tvPostTitle.text = posts.title
                tvPostUserName.text = posts.userName
                tvPostUserCompany.text = posts.company

                itemView.setOnClickListener {
                    onItemClick?.invoke(posts)
                }
            }
        }
    }
}