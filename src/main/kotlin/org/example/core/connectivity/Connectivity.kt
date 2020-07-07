package org.example.core.connectivity

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Singleton

interface Connectivity {
    val isOnline: Observable<Boolean>
    fun setOnline()
    fun setOffline()
}

@Singleton
class ConnectivityImpl : Connectivity {
    override val isOnline: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(true)

    override fun setOnline() = isOnline.onNext(true)

    override fun setOffline() = isOnline.onNext(false)
}
