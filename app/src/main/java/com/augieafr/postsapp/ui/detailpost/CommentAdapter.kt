package com.augieafr.postsapp.ui.detailpost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.augieafr.postsapp.data.source.local.entity.CommentEntity
import com.augieafr.postsapp.databinding.CommentItemsBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val listComment = ArrayList<CommentEntity>()

    fun setData(data: List<CommentEntity>?) {
        if (data == null) return
        listComment.clear()
        listComment.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding =
            CommentItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = listComment[position]
        holder.bind(comment)
    }

    override fun getItemCount(): Int = listComment.size


    inner class CommentViewHolder(private val binding: CommentItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentEntity) {
            with(binding) {
                tvCommentUsername.text = comment.name
                tvCommentBody.text = comment.body
            }
        }
    }

}