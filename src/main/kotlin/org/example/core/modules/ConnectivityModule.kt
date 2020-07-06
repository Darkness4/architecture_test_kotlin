package org.example.core.modules

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import org.example.core.connectivity.Connectivity
import org.example.core.connectivity.ConnectivityImpl

@Module
class ConnectivityModule {
    @Provides
    @Singleton
    fun provideConnectivity(): Connectivity = ConnectivityImpl()
}
