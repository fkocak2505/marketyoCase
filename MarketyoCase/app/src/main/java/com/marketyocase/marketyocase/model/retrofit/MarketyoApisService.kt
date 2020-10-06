package com.marketyocase.marketyocase.model.retrofit

import com.marketyocase.marketyocase.model.commentsPost.Response4CommentsPost
import com.marketyocase.marketyocase.model.user.Response4Users
import com.marketyocase.marketyocase.model.userPost.Response4UserPost
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MarketyoApisService {

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(IApiService::class.java)


    fun getUserList(): Single<Response<MutableList<Response4Users>>> {
        return api.getUserList()
    }


    fun getUserPostList(userId: Int): Single<Response<MutableList<Response4UserPost>>> {
        return api.getUserPostList(userId)
    }


    fun getCommentsPost(postId: Int): Single<Response<MutableList<Response4CommentsPost>>> {
        return api.getCommentsPost(postId)
    }



}