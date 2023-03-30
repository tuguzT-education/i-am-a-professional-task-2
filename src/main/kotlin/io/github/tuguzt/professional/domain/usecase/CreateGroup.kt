package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupDetails
import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.repository.GroupRepository

class CreateGroup(private val repository: GroupRepository) {
    suspend fun createGroup(create: GroupDetails): GroupId {
        return repository.create(create)
    }
}
