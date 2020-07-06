package org.example.core.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import org.example.data.datasources.GithubRemoteDataSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RetrofitModule {
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
