package io.github.tuguzt.professional.app

import io.github.tuguzt.professional.app.di.configureDI
import io.github.tuguzt.professional.app.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

@Suppress("unused")
fun Application.module() {
    configureDI()
    configureRouting()
}
