package io.github.tuguzt.professional.domain.model

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class GroupId(val value: Long)

@Serializable
data class Group(
    val id: GroupId,
    val name: String,
    val description: String,
    val participants: List<Participant>,
)

@Serializable
data class GroupDetails(
    val name: String,
    val description: String = "",
)

@Serializable
data class GroupSummary(
    val id: GroupId,
    val name: String,
    val description: String,
)

fun Group.toSummary(): GroupSummary = GroupSummary(id, name, description)
