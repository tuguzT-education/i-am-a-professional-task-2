package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupSummary
import io.github.tuguzt.professional.domain.repository.GroupRepository

class GetAllGroups(private val repository: GroupRepository) {
    suspend fun getAllGroups(): List<GroupSummary> {
        return repository.readAll()
    }
}
