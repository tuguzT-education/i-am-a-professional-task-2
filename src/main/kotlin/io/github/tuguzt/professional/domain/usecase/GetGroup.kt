package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.Group
import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.repository.GroupRepository

class GetGroup(private val repository: GroupRepository) {
    suspend fun getGroup(id: GroupId): Group? {
        return repository.read(id)
    }
}
