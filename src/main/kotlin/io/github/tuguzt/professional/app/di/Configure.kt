package io.github.tuguzt.professional.app.di

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    val jdbcUrl = environment.config.property("database.url").getString()
    val driverName = environment.config.property("database.driver").getString()

    val dataModule = createDataModule(driverName, jdbcUrl)
    val domainModule = createDomainModule()
    install(Koin) {
        slf4jLogger()
        modules(dataModule, domainModule)
    }
}
