package io.github.tuguzt.professional.data.repository

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.model.GroupEntity
import io.github.tuguzt.professional.data.model.ParticipantEntity
import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.Participant
import io.github.tuguzt.professional.domain.model.ParticipantDetails
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.repository.ParticipantRepository

class ParticipantRepositoryImpl(private val client: RepositoryClient) : ParticipantRepository {
    override suspend fun create(groupId: GroupId, create: ParticipantDetails): ParticipantId = client.transaction {
        val groupEntity = GroupEntity.findById(groupId.value)
        val entity = ParticipantEntity.new {
            name = create.name
            wish = create.wish
            group = checkNotNull(groupEntity)
        }
        ParticipantId(entity.id.value)
    }

    override suspend fun read(groupId: GroupId, participantId: ParticipantId): Participant? = client.transaction {
        val entity = ParticipantEntity.findById(participantId.value)
        entity?.toDomain()
    }

    override suspend fun update(
        groupId: GroupId,
        participantId: ParticipantId,
        recipientId: ParticipantId,
    ): Participant = client.transaction {
        val participant = ParticipantEntity.findById(participantId.value)
        checkNotNull(participant)
        val recipient = ParticipantEntity.findById(recipientId.value)
        checkNotNull(recipient)

        participant.recipient = recipient
        participant.toDomain()
    }

    override suspend fun delete(groupId: GroupId, participantId: ParticipantId) = client.transaction {
        val entity = ParticipantEntity.findById(participantId.value)
        checkNotNull(entity).delete()
    }
}
