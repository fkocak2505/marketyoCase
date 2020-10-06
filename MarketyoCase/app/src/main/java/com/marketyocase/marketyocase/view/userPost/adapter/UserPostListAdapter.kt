package com.marketyocase.marketyocase.view.userPost.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marketyocase.marketyocase.R
import com.marketyocase.marketyocase.model.userPost.Response4UserPost

class UserPostListAdapter(
    private val context: Context,
    private val usersPostItem: MutableList<Response4UserPost>,
    private val listener: (View, Response4UserPost) -> Unit
) : RecyclerView.Adapter<UserPostListAdapter.ViewHolder>() {

    fun updatePlayList(newResponse4PLayList: MutableList<Response4UserPost>) {
        usersPostItem.clear()
        usersPostItem.addAll(newResponse4PLayList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_user_post_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(context, usersPostItem[position], listener)

    }

    override fun getItemCount(): Int {
        return usersPostItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            context: Context,
            usersItem: Response4UserPost,
            listener: (View, Response4UserPost) -> Unit
        ) {
            val userTitle =
                itemView.findViewById<TextView>(R.id.userTitle)

            val showMore =
                itemView.findViewById<TextView>(R.id.showMore)


            userTitle.text = usersItem.title


            itemView.setOnClickListener {
                listener(it, usersItem)
            }

            showMore.setOnClickListener {
                listener(it, usersItem)
            }
        }
    }

}