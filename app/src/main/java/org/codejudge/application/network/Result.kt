package org.codejudge.application.network

/**
 * @author Rolbin, created on 13-11-2020.
 */
sealed class Result<out ResponseType> {

    data class Success<out ResponseType>(val data: ResponseType) : Result<ResponseType>()

    data class Failure(val throwable: Throwable) : Result<Nothing>()
}