package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ParticipantEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ParticipantEntity>(ParticipantTable)

    var name by ParticipantTable.name
    var wish by ParticipantTable.wish
    var recipient by ParticipantEntity optionalReferencedOn ParticipantTable.recipient
    var group by GroupEntity referencedOn ParticipantTable.group
}
