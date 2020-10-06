package com.marketyocase.marketyocase.view.commentsPost.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marketyocase.marketyocase.R
import com.marketyocase.marketyocase.model.commentsPost.Response4CommentsPost

class PostCommentsListAdapter(
    private val context: Context,
    private val commentPostItem: MutableList<Response4CommentsPost>,
    private val listener: (View, Response4CommentsPost) -> Unit
) : RecyclerView.Adapter<PostCommentsListAdapter.ViewHolder>() {

    fun updatePlayList(newResponse4PLayList: MutableList<Response4CommentsPost>) {
        commentPostItem.clear()
        commentPostItem.addAll(newResponse4PLayList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_comments_post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(context, commentPostItem[position], listener)

    }

    override fun getItemCount(): Int {
        return commentPostItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            context: Context,
            commentPostItem: Response4CommentsPost,
            listener: (View, Response4CommentsPost) -> Unit
        ) {
            val commentsName =
                itemView.findViewById<TextView>(R.id.commentsName)

            commentsName.text = commentPostItem.name

            itemView.setOnClickListener {
                listener(it, commentPostItem)
            }
        }
    }

}