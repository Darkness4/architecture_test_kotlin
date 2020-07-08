package org.example.core.usecases

import io.reactivex.rxjava3.core.*

interface Usecase<P, T> {
    operator fun invoke(params: P): T
}

interface SingleUsecase<P, T> {
    operator fun invoke(params: P): Single<T>
}

interface MaybeUsecase<P, T> {
    operator fun invoke(params: P): Maybe<T>
}

interface FlowableUsecase<P, T> {
    operator fun invoke(params: P): Flowable<T>
}

interface ObservableUsecase<P, T> {
    operator fun invoke(params: P): Observable<T>
}

interface CompletableUsecase<P> {
    operator fun invoke(params: P): Completable
}

