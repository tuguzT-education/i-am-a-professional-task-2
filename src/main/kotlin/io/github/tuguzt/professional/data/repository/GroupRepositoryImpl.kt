package io.github.tuguzt.professional.data.repository

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.model.GroupEntity
import io.github.tuguzt.professional.domain.model.*
import io.github.tuguzt.professional.domain.repository.GroupRepository

class GroupRepositoryImpl(private val client: RepositoryClient) : GroupRepository {
    override suspend fun readAll(): List<GroupSummary> = client.transaction {
        val entities = GroupEntity.all()
        entities.map { entity -> entity.toDomain().toSummary() }
    }

    override suspend fun create(create: GroupDetails): GroupId = client.transaction {
        val entity = GroupEntity.new {
            name = create.name
            description = create.description
        }
        GroupId(value = entity.id.value)
    }

    override suspend fun read(id: GroupId): Group? = client.transaction {
        val entity = GroupEntity.findById(id.value)
        entity?.toDomain()
    }

    override suspend fun update(id: GroupId, update: GroupDetails) = client.transaction {
        val entity = GroupEntity.findById(id.value)
        checkNotNull(entity).run {
            name = update.name
            description = update.description
        }
    }

    override suspend fun delete(id: GroupId) = client.transaction {
        val entity = GroupEntity.findById(id.value)
        checkNotNull(entity).delete()
    }
}
