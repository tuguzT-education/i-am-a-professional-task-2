package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.repository.GroupRepository

class DeleteGroup(private val repository: GroupRepository) {
    suspend fun deleteGroup(id: GroupId) {
        return repository.delete(id)
    }
}
