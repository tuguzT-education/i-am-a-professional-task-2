package io.github.tuguzt.professional.app.routing

import io.github.tuguzt.professional.domain.model.GroupDetails
import io.github.tuguzt.professional.domain.model.ParticipantDetails
import io.github.tuguzt.professional.domain.usecase.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    install(ContentNegotiation) {
        json()
    }
    install(Resources)

    routing {
        openAPI(path = "openapi")
        swaggerUI(path = "swagger")
    }

    routing {
        createGroup()
        createParticipant()
        deleteGroup()
        deleteParticipant()
        getAllGroups()
        getGroup()
        getRecipient()
        tossGroup()
        updateGroup()
    }
}

fun Routing.createGroup() {
    val interactor: CreateGroup by inject()

    post<Group> {
        val create = call.receive<GroupDetails>()
        val id = interactor.createGroup(create)
        call.respond(HttpStatusCode.Created, id)
    }
}

fun Routing.createParticipant() {
    val interactor: CreateParticipant by inject()

    post<Group.Id.Participant> {
        val groupId = it.parent.groupId
        val create = call.receive<ParticipantDetails>()
        val id = interactor.createParticipant(groupId, create)
        call.respond(HttpStatusCode.Created, id)
    }
}

fun Routing.deleteGroup() {
    val interactor: DeleteGroup by inject()

    delete<Group.Id> {
        val groupId = it.groupId
        interactor.deleteGroup(groupId)
        call.respond(HttpStatusCode.NoContent)
    }
}

fun Routing.deleteParticipant() {
    val interactor: DeleteParticipant by inject()

    delete<Group.Id.Participant.Id> {
        val groupId = it.parent.parent.groupId
        val participantId = it.participantId
        interactor.deleteParticipant(groupId, participantId)
        call.respond(HttpStatusCode.NoContent)
    }
}

fun Routing.getAllGroups() {
    val interactor: GetAllGroups by inject()

    get<Groups> {
        val groups = interactor.getAllGroups()
        call.respond(groups)
    }
}

fun Routing.getGroup() {
    val interactor: GetGroup by inject()

    get<Group.Id> {
        val id = it.groupId
        when (val group = interactor.getGroup(id)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(group)
        }
    }
}

fun Routing.getRecipient() {
    val interactor: GetRecipient by inject()

    get<Group.Id.Participant.Id.Recipient> {
        val groupId = it.parent.parent.parent.groupId
        val participantId = it.parent.participantId
        when (val recipient = interactor.getRecipient(groupId, participantId)) {
            null -> call.respond(HttpStatusCode.NotFound)
            else -> call.respond(recipient)
        }
    }
}

fun Routing.tossGroup() {
    val interactor: TossGroup by inject()

    post<Group.Id.Toss> {
        val id = it.parent.groupId
        try {
            val participants = interactor.tossGroup(id)
            call.respond(participants)
        } catch (e: TossGroup.Exception) {
            call.respond(HttpStatusCode.Conflict)
        }
    }
}

fun Routing.updateGroup() {
    val interactor: UpdateGroup by inject()

    put<Group.Id> {
        val id = it.groupId
        val update = call.receive<GroupDetails>()
        interactor.updateGroup(id, update)
        call.respond(HttpStatusCode.NoContent)
    }
}
