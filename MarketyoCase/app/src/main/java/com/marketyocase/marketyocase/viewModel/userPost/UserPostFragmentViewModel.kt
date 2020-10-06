package com.marketyocase.marketyocase.viewModel.userPost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marketyocase.marketyocase.IRequestListener
import com.marketyocase.marketyocase.model.userPost.Response4UserPost
import com.marketyocase.marketyocase.repositories.userPost.UserPostListDataSourceImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response

class UserPostFragmentViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val userPostDataSourceImpl = UserPostListDataSourceImpl(disposable)

    val userPostList = MutableLiveData<MutableList<Response4UserPost>>()
    val userPostListError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    //==============================================================================================
    /**
     * User Post List View Model method with userId parameter
     */
    //==============================================================================================
    fun getUserPostList(userId: Int) {
        loading.value = true
        userPostDataSourceImpl.getUserPostList(userId, object : IRequestListener<MutableList<Response4UserPost>> {
            override fun onSuccess(response: Response<MutableList<Response4UserPost>>) {
                userPostList.value = response.body()!!
                userPostListError.value = false
                loading.value = false
            }

            override fun onUnSuccess(response: Response<MutableList<Response4UserPost>>) {
                userPostListError.value = true
                loading.value = false
            }

            override fun onFail(t: Throwable?) {
                userPostListError.value = true
                loading.value = false
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}