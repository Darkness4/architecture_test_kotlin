package org.example.core.modules

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import org.example.data.datasources.GithubRemoteDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitModule: AbstractModule() {
    @Provides
    @Singleton
    fun provideGithubRemoteDataSource(): GithubRemoteDataSource {
        return Retrofit.Builder()
            .baseUrl(GithubRemoteDataSource.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GithubRemoteDataSource::class.java)
    }
}
