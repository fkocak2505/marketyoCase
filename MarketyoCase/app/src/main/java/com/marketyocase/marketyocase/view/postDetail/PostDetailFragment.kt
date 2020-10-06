package com.marketyocase.marketyocase.view.postDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.marketyocase.marketyocase.R
import com.marketyocase.marketyocase.model.userPost.Response4UserPost
import kotlinx.android.synthetic.main.fragment_post_detail.*

class PostDetailFragment : Fragment() {

    private lateinit var postId: String

    //==============================================================================================
    /**
     * Fragment onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    //==============================================================================================
    /**
     * Fragment OnViewCreated Method..
     */
    //==============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString("userPostItem")?.let { postItem ->

                val userPostItem = Gson().fromJson(postItem, Response4UserPost::class.java)
                userPostTitle.text = userPostItem.title
                userPostBody.text = userPostItem.body

                postId = userPostItem.id.toString()
            }
        }

        readComment.setOnClickListener {
            Navigation.findNavController(view).navigate(
                PostDetailFragmentDirections.actionPostDetail2PostComments(postId)
            )
        }

        postDetailCardView.setOnClickListener {
            Navigation.findNavController(view).navigate(
                PostDetailFragmentDirections.actionPostDetail2PostComments(postId)
            )
        }

    }
}