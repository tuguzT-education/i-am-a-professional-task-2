package io.github.tuguzt.professional.app.di

import io.github.tuguzt.professional.domain.usecase.*
import org.koin.core.module.Module
import org.koin.dsl.module

fun createDomainModule(): Module = module {
    factory { CreateGroup(get()) }
    factory { CreateParticipant(get()) }
    factory { DeleteGroup(get()) }
    factory { DeleteParticipant(get()) }
    factory { GetAllGroups(get()) }
    factory { GetGroup(get()) }
    factory { GetRecipient(get()) }
    factory { TossGroup(get(), get()) }
    factory { UpdateGroup(get()) }
}
