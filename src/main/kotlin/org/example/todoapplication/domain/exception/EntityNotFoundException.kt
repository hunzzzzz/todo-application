package org.example.todoapplication.domain.exception

data class EntityNotFoundException(
    val id: Long, val entityName: String
) : RuntimeException("Entity '$entityName' not found with given id $id.")