package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.model.Recipient
import io.github.tuguzt.professional.domain.repository.ParticipantRepository

class GetRecipient(private val repository: ParticipantRepository) {
    suspend fun getRecipient(groupId: GroupId, participantId: ParticipantId): Recipient? {
        val participant = repository.read(groupId, participantId)
        return participant?.recipient
    }
}
