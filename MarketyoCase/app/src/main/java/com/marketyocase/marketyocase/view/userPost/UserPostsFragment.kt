package com.marketyocase.marketyocase.view.userPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.marketyocase.marketyocase.R
import com.marketyocase.marketyocase.model.user.Response4Users
import com.marketyocase.marketyocase.model.userPost.Response4UserPost
import com.marketyocase.marketyocase.view.userPost.adapter.UserPostListAdapter
import com.marketyocase.marketyocase.viewModel.userPost.UserPostFragmentViewModel
import kotlinx.android.synthetic.main.fragment_user_posts.*

class UserPostsFragment : Fragment() {

    private lateinit var viewModel: UserPostFragmentViewModel

    private lateinit var userPostsAdapter: UserPostListAdapter

    //==============================================================================================
    /**
     * Fragment onCreate Method..
     */
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserPostFragmentViewModel::class.java)

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
        return inflater.inflate(R.layout.fragment_user_posts, container, false)
    }

    //==============================================================================================
    /**
     * Fragment OnViewCreated Method..
     */
    //==============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString("userId")?.let { userPostItem ->

                val userItem = Gson().fromJson(userPostItem, Response4Users::class.java)
                viewModel.getUserPostList(userItem.id!!)
            }
        }

        prepareUserPostRecyclerView()

        observeViewModel()
    }

    //==============================================================================================
    /**
     * Prepare RecyclerView Data..
     */
    //==============================================================================================
    private fun prepareUserPostRecyclerView() {
        val mLayoutManager =
            LinearLayoutManager(activity?.applicationContext!!, RecyclerView.VERTICAL, false)

        userPostsAdapter = UserPostListAdapter(
            activity?.applicationContext!!,
            arrayListOf()
        ) { view, itemOfUserPostData ->
            handleClickUserPostsItem(view, itemOfUserPostData)
        }

        userPostList.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = userPostsAdapter
        }
    }

    //==============================================================================================
    /**
     * Handle click User Post Item..
     */
    //==============================================================================================
    private fun handleClickUserPostsItem(view: View, itemOfUserPostData: Response4UserPost) {
        Navigation.findNavController(view).navigate(
            UserPostsFragmentDirections.actionUserPostFragment2PostDetailFragment(
                Gson().toJson(
                    itemOfUserPostData
                )
            )
        )
    }

    //==============================================================================================
    /**
     * Observer Data from API..
     */
    //==============================================================================================
    private fun observeViewModel() {
        viewModel.userPostList.observe(viewLifecycleOwner, Observer { playListData ->
            playListData?.let {
                userPostList.visibility = View.VISIBLE

                userPostsAdapter.updatePlayList(playListData)

            }
        })

        viewModel.userPostListError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                userPostListError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                userPostLoadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    userPostListError.visibility = View.GONE
                    userPostList.visibility = View.GONE
                }
            }
        })
    }
}