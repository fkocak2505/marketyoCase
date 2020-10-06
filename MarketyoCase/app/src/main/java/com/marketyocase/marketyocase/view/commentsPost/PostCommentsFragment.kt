package com.marketyocase.marketyocase.view.commentsPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marketyocase.marketyocase.R
import com.marketyocase.marketyocase.model.commentsPost.Response4CommentsPost
import com.marketyocase.marketyocase.view.commentsPost.adapter.PostCommentsListAdapter
import com.marketyocase.marketyocase.viewModel.commentsPost.PostCommentFragmentViewModel
import kotlinx.android.synthetic.main.fragment_post_comments.*


class PostCommentsFragment : Fragment() {

    private lateinit var viewModel: PostCommentFragmentViewModel

    private lateinit var postCommentsAdapter: PostCommentsListAdapter

    private var postId: Int = 0

    //==============================================================================================
    /**
     * Fragment onCreate Method..
     */
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PostCommentFragmentViewModel::class.java)
    }

    //==============================================================================================
    /**
     * Fragment onCreateView Method..
     */
    //==============================================================================================
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_comments, container, false)
    }

    //==============================================================================================
    /**
     * Fragment OnViewCreated Method..
     */
    //==============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString("postId")?.let { valOfPostId ->

                postId = valOfPostId.toInt()
                viewModel.getPostComment(postId)
            }
        }

        preparePostCommentsRecyclerView()

        observeViewModel()

    }

    //==============================================================================================
    /**
     * Prepare RecyclerView Data..
     */
    //==============================================================================================
    private fun preparePostCommentsRecyclerView() {
        val mLayoutManager =
            LinearLayoutManager(activity?.applicationContext!!, RecyclerView.VERTICAL, false)

        postCommentsAdapter = PostCommentsListAdapter(
            activity?.applicationContext!!,
            arrayListOf()
        ) { view, itemOfUserPostData ->
            handleClickCommentsPostItem(view, itemOfUserPostData)
        }

        commentsPostList.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = postCommentsAdapter
        }
    }

    //==============================================================================================
    /**
     * Handle click Post Comments Item..
     */
    //==============================================================================================
    private fun handleClickCommentsPostItem(view: View, itemOfUserPostData: Response4CommentsPost) {
        Toast.makeText(
            activity?.applicationContext,
            "${itemOfUserPostData.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    //==============================================================================================
    /**
     * Observer Data from API..
     */
    //==============================================================================================
    private fun observeViewModel() {
        viewModel.commentsPostList.observe(viewLifecycleOwner, Observer { postCommentsItem ->
            postCommentsItem?.let {
                commentsPostList.visibility = View.VISIBLE

                postCommentsAdapter.updatePlayList(postCommentsItem)

            }
        })

        viewModel.commentsPostListError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                commentsPostListError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                commentsPostLoadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    commentsPostListError.visibility = View.GONE
                    commentsPostList.visibility = View.GONE
                }
            }
        })
    }
}