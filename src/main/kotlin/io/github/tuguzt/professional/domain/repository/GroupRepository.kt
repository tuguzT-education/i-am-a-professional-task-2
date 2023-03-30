package io.github.tuguzt.professional.domain.repository

import io.github.tuguzt.professional.domain.model.Group
import io.github.tuguzt.professional.domain.model.GroupDetails
import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.GroupSummary

interface GroupRepository {
    suspend fun readAll(): List<GroupSummary>

    suspend fun create(create: GroupDetails): GroupId

    suspend fun read(id: GroupId): Group?

    suspend fun update(id: GroupId, update: GroupDetails)

    suspend fun delete(id: GroupId)
}
