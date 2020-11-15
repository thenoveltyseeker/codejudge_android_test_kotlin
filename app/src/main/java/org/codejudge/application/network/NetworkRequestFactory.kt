package org.codejudge.application.network

import retrofit2.Retrofit

/**
 * @author Rolbin, created on 13-11-2020.
 */
class NetworkRequestFactory(private val retrofitClient: Retrofit) {

    fun <RequestApi> createApiService(service: Class<RequestApi>): RequestApi {
        return retrofitClient.create(service)
    }

    internal inline fun <ResponseType> performRequest(request: () -> ResponseType): Result<ResponseType> {
        return try {
            with(request.invoke()) {
                Result.Success(this)
            }
        } catch (throwable: Throwable) {
            Result.Failure(throwable)
        }
    }
}