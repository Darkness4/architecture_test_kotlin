package org.example.core.modules

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import org.example.core.connectivity.Connectivity
import org.example.core.connectivity.ConnectivityImpl

class ConnectivityModule: AbstractModule() {
    @Provides
    @Singleton
    fun provideConnectivity(impl: ConnectivityImpl): Connectivity = impl
}
