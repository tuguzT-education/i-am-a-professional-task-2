package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.repository.ParticipantRepository

class DeleteParticipant(private val repository: ParticipantRepository) {
    suspend fun deleteParticipant(groupId: GroupId, participantId: ParticipantId) {
        return repository.delete(groupId, participantId)
    }
}
