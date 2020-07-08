package org.example.core.connectivity

import io.reactivex.rxjava3.core.Observable

interface Connectivity {
    val isOnline: Observable<Boolean>
    fun setOnline()
    fun setOffline()
}