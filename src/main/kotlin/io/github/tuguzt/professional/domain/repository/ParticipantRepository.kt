package io.github.tuguzt.professional.domain.repository

import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.Participant
import io.github.tuguzt.professional.domain.model.ParticipantDetails
import io.github.tuguzt.professional.domain.model.ParticipantId

interface ParticipantRepository {
    suspend fun create(groupId: GroupId, create: ParticipantDetails): ParticipantId

    suspend fun read(groupId: GroupId, participantId: ParticipantId): Participant?

    suspend fun update(groupId: GroupId, participantId: ParticipantId, recipientId: ParticipantId): Participant

    suspend fun delete(groupId: GroupId, participantId: ParticipantId)
}
