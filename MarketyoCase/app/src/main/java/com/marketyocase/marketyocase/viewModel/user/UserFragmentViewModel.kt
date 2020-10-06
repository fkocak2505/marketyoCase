package com.marketyocase.marketyocase.viewModel.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marketyocase.marketyocase.IRequestListener
import com.marketyocase.marketyocase.model.user.Response4Users
import com.marketyocase.marketyocase.repositories.user.UserListDataSourceImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response

class UserFragmentViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val userListDataSourceImpl = UserListDataSourceImpl(disposable)

    val userList = MutableLiveData<MutableList<Response4Users>>()
    val userListError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    //==============================================================================================
    /**
     * Trigger User List When Fragment Start..
     */
    //==============================================================================================
    fun refresh() {
        getUserList()
    }

    //==============================================================================================
    /**
     * UserList View Model method
     */
    //==============================================================================================
    private fun getUserList() {
        loading.value = true
        userListDataSourceImpl.getUserList(object : IRequestListener<MutableList<Response4Users>> {
            override fun onSuccess(response: Response<MutableList<Response4Users>>) {
                userList.value = response.body()!!
                userListError.value = false
                loading.value = false
            }

            override fun onUnSuccess(response: Response<MutableList<Response4Users>>) {
                userListError.value = true
                loading.value = false
            }

            override fun onFail(t: Throwable?) {
                userListError.value = true
                loading.value = false
            }
        })
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}