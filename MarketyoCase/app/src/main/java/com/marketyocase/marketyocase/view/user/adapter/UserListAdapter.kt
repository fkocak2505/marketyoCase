package com.marketyocase.marketyocase.view.user.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marketyocase.marketyocase.R
import com.marketyocase.marketyocase.model.user.Response4Users

class UserListAdapter(
    private val context: Context,
    private val usersItem: MutableList<Response4Users>,
    private val listener: (View, Response4Users) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>()  {

    fun updatePlayList(newResponse4PLayList: MutableList<Response4Users>) {
        usersItem.clear()
        usersItem.addAll(newResponse4PLayList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_users_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(context, usersItem[position], listener)

    }

    override fun getItemCount(): Int {
        return usersItem.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            context: Context,
            usersItem: Response4Users,
            listener: (View, Response4Users) -> Unit
        ) {
            val usersName =
                itemView.findViewById<TextView>(R.id.usersName)

            val userEmail =
                itemView.findViewById<TextView>(R.id.userEmail)


            usersName.text = usersItem.username
            userEmail.text = usersItem.email


            itemView.setOnClickListener {
                listener(it, usersItem)
            }
        }
    }

}