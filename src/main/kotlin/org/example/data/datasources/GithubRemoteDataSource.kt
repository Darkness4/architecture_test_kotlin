package org.example.data.datasources

import io.reactivex.rxjava3.core.Single
import org.example.data.models.RepoModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubRemoteDataSource {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users/{user}/repos")
    fun getReposByUser(@Path("user") user: String): Single<List<RepoModel>>
}
