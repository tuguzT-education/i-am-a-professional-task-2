package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupDetails
import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.repository.GroupRepository

class UpdateGroup(private val repository: GroupRepository) {
    suspend fun updateGroup(id: GroupId, update: GroupDetails) {
        return repository.update(id, update)
    }
}
