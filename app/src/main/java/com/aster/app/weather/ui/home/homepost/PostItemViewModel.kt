package com.aster.app.weather.ui.home.homepost

import com.aster.app.weather.data.model.Post
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.ui.base.BaseItemViewModel
import com.aster.app.weather.utils.network.NetworkHelper
import com.aster.app.weather.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostItemViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
                                            compositeDisposable: CompositeDisposable,
                                            networkHelper: NetworkHelper,
private val weatherForecastRepository: WeatherForecastRepository)
    : BaseItemViewModel<Post>(schedulerProvider,compositeDisposable,networkHelper)
{
    companion object {
        const val TAG = "PostItemViewModel"
    }

    override fun onCreate() {
        TODO("Not yet implemented")
    }
/*

    private val screenWidth = ScreenUtils.getScreenWidth()
    private val screenHeight = ScreenUtils.getScreenHeight()
    private val headers = mapOf(
        Pair(Networking.HEADER_API_KEY, Networking.API_KEY),
        Pair(Networking.HEADER_USER_ID, user.id),
        Pair(Networking.HEADER_ACCESS_TOKEN, user.accessToken)
    )

    val name : LiveData<String> = Transformations.map(data){it.createdUser.name}
    val postTime : LiveData<String> = Transformations.map(data){TimeUtils.getTimeAgo(it.createdAt)}
    val likeCounts : LiveData<Int> = Transformations.map(data){ it.likedBy?.size ?: 0}
    val isLiked: LiveData<Boolean> = Transformations.map(data){
        it.likedBy?.filter { postUser-> postUser.userId == user.id } !=null
    }

    val profileImage: LiveData<com.aster.app.weather.data.model.Image> = Transformations.map(data) {
        it.createdUser.profilePicUrl?.run {
            com.aster.app.weather.data.model.Image(
                this,
                headers
            )
        }
    }

    val imageDetail: LiveData<com.aster.app.weather.data.model.Image> = Transformations.map(data) {
        com.aster.app.weather.data.model.Image(
            it.imgUrl,
            headers,
            screenWidth,
            it.imgHeight?.let { height ->
                return@let (calculateScaleFactor(it) * height).toInt()
            } ?: screenHeight / 3)
    }

    private fun calculateScaleFactor(post: Post) =
        post.imgWidth?.let { return@let screenWidth.toFloat() / it } ?: 1f

    override fun onCreate() {

    }



    fun onLikeClick() = data.value.let {

        if(networkHelper.isNetworkConnected())
        {
           val api =
               if (isLiked.value == true)

                   weatherRepository.makeUnlikePost(it!!,user)

                else
                    weatherRepository.makeLikePost(it!!,user)

            compositeDisposable.add(api
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        responsePost -> if (responsePost.id == it.id )
                        updateData(responsePost)
                    },{
                        error-> handleNetworkError(error)
                    }
                )
            )

        }
        else
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
    }
*/
}




