package org.example.presentation

import com.google.inject.Guice
import org.example.core.modules.ApplicationModule
import org.example.core.modules.ConnectivityModule
import org.example.core.modules.RetrofitModule
import org.example.presentation.views.GithubView
import tornadofx.App
import tornadofx.DIContainer
import tornadofx.FX
import kotlin.reflect.KClass

class MyApp: App(GithubView::class) {
    init {
        val guice = Guice.createInjector(RetrofitModule(), ConnectivityModule(), ApplicationModule())

        FX.dicontainer = object : DIContainer {
            override fun <T : Any> getInstance(type: KClass<T>): T
                    = guice.getInstance(type.java)
        }
    }
}