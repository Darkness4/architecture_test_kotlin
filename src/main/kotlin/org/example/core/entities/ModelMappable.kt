package org.example.core.entities

interface ModelMappable<R> {
    fun asModel(): R
}