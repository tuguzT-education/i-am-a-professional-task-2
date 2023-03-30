package io.github.tuguzt.professional.app.routing

import io.github.tuguzt.professional.domain.model.GroupId
import io.github.tuguzt.professional.domain.model.ParticipantId
import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/group")
class Group {
    @Serializable
    @Resource("{groupId}")
    class Id(val parent: Group, val groupId: GroupId) {
        @Serializable
        @Resource("participant")
        class Participant(val parent: Group.Id) {
            @Serializable
            @Resource("{participantId}")
            class Id(val parent: Participant, val participantId: ParticipantId) {
                @Serializable
                @Resource("recipient")
                class Recipient(val parent: Id)
            }
        }

        @Serializable
        @Resource("toss")
        class Toss(val parent: Id)
    }
}

@Serializable
@Resource("/groups")
class Groups
