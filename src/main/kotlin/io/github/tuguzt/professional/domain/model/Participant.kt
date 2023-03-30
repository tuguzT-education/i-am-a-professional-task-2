package io.github.tuguzt.professional.domain.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class ParticipantId(val value: Long)

@Serializable
data class Participant(
    val id: ParticipantId,
    val name: String,
    val wish: String,
    val recipient: Recipient?,
)

fun Participant.toRecipient(): Recipient = Recipient(id, name, wish)

@Serializable
data class Recipient(
    val id: ParticipantId,
    val name: String,
    val wish: String,
)

@Serializable
data class ParticipantDetails(
    val name: String,
    val wish: String = "",
)
