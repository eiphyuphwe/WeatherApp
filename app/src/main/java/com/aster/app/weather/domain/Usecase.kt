package com.aster.app.weather.domain


abstract class Usecase {


    /**
     * This is sealed class that contains the input from presentation layer to the domain layer
     */
    sealed class Input {
        object None
        class Single<Data : Any>(val data: Data)
        class Multiple<Data : Any>(vararg val data: Data)
    }

    /**
     * This is the sealed that contains either Success or Error classes. It can be only one of the two.
     * In case of success, Failure will be nothing, data will contain object of type T
     * In case of error, Failure will be present, data will be nothing
     * This is the class that contains the output from the domain layer to the the presentation layet
     */
    sealed class Output<out FailureType : Failure, out T> {

        class Success<out T>(val data: T) : Output<Nothing, T>()

        class Error<out FailureType : Failure>(val failure: FailureType) :
            Output<FailureType, Nothing>()

        fun successOrError(
            functionFailure: (FailureType) -> Any,
            functionType: (T) -> Any
        ): Any = when (this) {
            is Error -> functionFailure(failure)
            is Success -> functionType(data)
        }
    }

    interface Failure


}