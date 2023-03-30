package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.ParticipantDetails
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.github.tuguzt.professional.domain.repository.ParticipantRepository

class CreateParticipant(private val repository: ParticipantRepository) {
    suspend fun createParticipant(groupId: GroupId, create: ParticipantDetails): ParticipantId {
        return repository.create(groupId, create)
    }
}
