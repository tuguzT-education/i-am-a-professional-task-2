package io.github.tuguzt.professional.data.repository

import io.github.tuguzt.professional.data.model.GroupEntity
import io.github.tuguzt.professional.data.model.ParticipantEntity
import io.github.tuguzt.professional.domain.model.*

fun GroupEntity.toDomain(): Group = Group(
    id = GroupId(id.value),
    name = name,
    description = description,
    participants = participants.map(ParticipantEntity::toDomain),
)

fun ParticipantEntity.toDomain(): Participant = Participant(
    id = ParticipantId(id.value),
    name = name,
    wish = wish,
    recipient = recipient?.toDomain()?.toRecipient(),
)
