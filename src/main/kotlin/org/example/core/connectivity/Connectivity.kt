package org.example.core.connectivity

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

interface Connectivity {
    val isOnline: Observable<Boolean>
    fun setOnline()
    fun setOffline()
}

class ConnectivityImpl : Connectivity {
    override val isOnline: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    override fun setOnline() = isOnline.onNext(true)

    override fun setOffline() = isOnline.onNext(false)
}
