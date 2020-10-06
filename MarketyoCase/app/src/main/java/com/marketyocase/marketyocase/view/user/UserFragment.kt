package com.marketyocase.marketyocase.view.user

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
import com.marketyocase.marketyocase.view.user.adapter.UserListAdapter
import com.marketyocase.marketyocase.viewModel.user.UserFragmentViewModel
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private lateinit var viewModel: UserFragmentViewModel

    private lateinit var userListAdapter: UserListAdapter

    //==============================================================================================
    /**
     * Fragment onCreate Method..
     */
    //==============================================================================================
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(UserFragmentViewModel::class.java)
        viewModel.refresh()
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
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    //==============================================================================================
    /**
     * Fragment onViewCreated Method..
     */
    //==============================================================================================
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareUserRecyclerView()

        observeViewModel()

    }

    //==============================================================================================
    /**
     * Prepare RecyclerView Data..
     */
    //==============================================================================================
    private fun prepareUserRecyclerView() {
        val mLayoutManager =
            LinearLayoutManager(activity?.applicationContext!!, RecyclerView.VERTICAL, false)

        userListAdapter = UserListAdapter(
            activity?.applicationContext!!,
            arrayListOf()
        ) { view, itemOfUserData ->
            handleClickUsersItem(view, itemOfUserData)
        }

        userList.apply {
            layoutManager = mLayoutManager
            setItemViewCacheSize(5)
            adapter = userListAdapter
        }
    }

    //==============================================================================================
    /**
     * Handle click User Item..
     */
    //==============================================================================================
    private fun handleClickUsersItem(view: View, itemOfUserData: Response4Users) {
        Navigation.findNavController(view).navigate(
            UserFragmentDirections.actionUserFragment2UserPostFragment(Gson().toJson(itemOfUserData))
        )
    }

    //==============================================================================================
    /**
     * Observer Data from API..
     */
    //==============================================================================================
    private fun observeViewModel() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { playListData ->
            playListData?.let {
                userList.visibility = View.VISIBLE

                userListAdapter.updatePlayList(playListData)

            }
        })

        viewModel.userListError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                userListError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                usersLoadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    userListError.visibility = View.GONE
                    userList.visibility = View.GONE
                }
            }
        })
    }
}