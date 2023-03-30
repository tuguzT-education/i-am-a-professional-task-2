package io.github.tuguzt.professional.data.model

import org.jetbrains.exposed.dao.id.LongIdTable

object ParticipantTable : LongIdTable() {
    val name = text("name")
    val wish = text("wish")
    val recipient = reference("recipient", ParticipantTable).nullable()
    val group = reference("group", GroupTable)
}
