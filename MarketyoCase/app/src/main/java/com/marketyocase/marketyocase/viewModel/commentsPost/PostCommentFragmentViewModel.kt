package com.marketyocase.marketyocase.viewModel.commentsPost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marketyocase.marketyocase.IRequestListener
import com.marketyocase.marketyocase.model.commentsPost.Response4CommentsPost
import com.marketyocase.marketyocase.repositories.commentsPost.PostCommentListDataSourceImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Response

class PostCommentFragmentViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val postCommentDataSourceImpl = PostCommentListDataSourceImpl(disposable)

    val commentsPostList = MutableLiveData<MutableList<Response4CommentsPost>>()
    val commentsPostListError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    //==============================================================================================
    /**
     * PostComment View Model method with postId Parameter
     */
    //==============================================================================================
    fun getPostComment(postId: Int){
        loading.value = true
        postCommentDataSourceImpl.getPostComment(postId, object :
            IRequestListener<MutableList<Response4CommentsPost>> {
            override fun onSuccess(response: Response<MutableList<Response4CommentsPost>>) {
                commentsPostList.value = response.body()!!
                commentsPostListError.value = false
                loading.value = false
            }

            override fun onUnSuccess(response: Response<MutableList<Response4CommentsPost>>) {
                commentsPostListError.value = true
                loading.value = false
            }

            override fun onFail(t: Throwable?) {
                commentsPostListError.value = true
                loading.value = false
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}