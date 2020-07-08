package org.example.core.models

interface DomainMappable<R> {
    fun asEntity(): R
}