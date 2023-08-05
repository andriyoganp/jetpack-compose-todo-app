package com.ayeee.domain.usecase

interface DeleteTaskUseCase {
    suspend fun delete(id: Long): Int
    suspend fun deleteAll(): Int
}