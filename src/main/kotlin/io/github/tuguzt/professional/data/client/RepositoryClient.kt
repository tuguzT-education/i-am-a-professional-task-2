package io.github.tuguzt.professional.data.client

import io.github.tuguzt.professional.data.model.GroupTable
import io.github.tuguzt.professional.data.model.ParticipantTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

class RepositoryClient(driverName: String, jdbcUrl: String) {
    private val database = Database.connect(jdbcUrl, driverName)

    init {
        transaction(database) {
            SchemaUtils.create(GroupTable, ParticipantTable)
        }
    }

    suspend fun <T> transaction(statement: suspend Transaction.() -> T): T =
        newSuspendedTransaction(Dispatchers.IO, database, statement = statement)
}
