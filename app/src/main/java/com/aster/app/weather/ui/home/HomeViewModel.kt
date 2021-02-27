package com.aster.app.weather.ui.home

import androidx.lifecycle.MutableLiveData
import com.aster.app.weather.data.model.Post
import com.aster.app.weather.data.repository.PostReposistory
import com.aster.app.weather.data.repository.UserRepository
import com.aster.app.weather.ui.base.BaseViewModel
import com.aster.app.weather.utils.common.Resource
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
                    schedulerProvider: SchedulerProvider,
                    compositeDisposable: CompositeDisposable,
                    networkHelper: NetworkHelper,
                    private val userRepository: UserRepository,
                    private val postReposistory: PostReposistory,
                    private val allPostsList : ArrayList<Post>,
                    private val paginator : PublishProcessor<Pair<String?,String?>>

                    ):BaseViewModel(schedulerProvider,compositeDisposable,networkHelper)
{

    val loading : MutableLiveData<Boolean> = MutableLiveData()
    val posts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    private val user = userRepository.getCurrentUser()

    init {
            compositeDisposable.add(

                paginator.onBackpressureDrop()
                    .doOnNext {
                        loading.postValue(true)
                    }
                    .concatMapSingle { pageIds -> postReposistory
                        .fetchHomePosts(pageIds.first,pageIds.second,user!!)
                        .subscribeOn(Schedulers.io())
                        .doOnError{
                            handleNetworkError(it)
                        }
                    }
                    .subscribe(
                        {
                            allPostsList.addAll(it)
                            loading.postValue(false)
                            posts.postValue(Resource.success(it))
                        },
                        {

                        }
                    )
            )
    }
    override fun onCreate() {

        loadMorePosts()
    }

    private fun loadMorePosts()
    {
        val firstPostId : String ? = if(allPostsList.isNotEmpty()) allPostsList.get(0).id else null
        val lastPostId : String ? = if(allPostsList.size>0) allPostsList.get(allPostsList.size-1).id else null
        if(checkInternetConnectionWithMessage()) paginator.onNext(Pair(firstPostId,lastPostId))

    }

    fun onLoadMore()
    {
        loadMorePosts()
    }

}