package io.github.tuguzt.professional.app.di

import io.github.tuguzt.professional.data.client.RepositoryClient
import io.github.tuguzt.professional.data.repository.GroupRepositoryImpl
import io.github.tuguzt.professional.data.repository.ParticipantRepositoryImpl
import io.github.tuguzt.professional.domain.repository.GroupRepository
import io.github.tuguzt.professional.domain.repository.ParticipantRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun createDataModule(driverName: String, jdbcUrl: String): Module = module {
    single { RepositoryClient(driverName, jdbcUrl) }
    factory<GroupRepository> { GroupRepositoryImpl(get()) }
    factory<ParticipantRepository> { ParticipantRepositoryImpl(get()) }
}
