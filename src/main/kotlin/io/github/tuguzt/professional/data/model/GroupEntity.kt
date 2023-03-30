package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GroupEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<GroupEntity>(GroupTable)

    var name by GroupTable.name
    var description by GroupTable.description

    val participants by ParticipantEntity referrersOn ParticipantTable.group
}
