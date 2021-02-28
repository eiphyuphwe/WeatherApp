package com.aster.app.weather.domain

import androidx.lifecycle.LiveData
import com.aster.app.weather.utils.common.Resource

abstract class Usecase<I, O> {


    abstract fun executeUsecase(input: I): LiveData<O>

    interface Input

    interface Output<T> {


    }

}