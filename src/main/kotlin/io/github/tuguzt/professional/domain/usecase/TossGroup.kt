package io.github.tuguzt.professional.domain.usecase

import io.github.tuguzt.professional.domain.model.*
import io.github.tuguzt.professional.domain.repository.GroupRepository
import io.github.tuguzt.professional.domain.repository.ParticipantRepository
import kotlin.random.Random

class TossGroup(
    private val groupRepository: GroupRepository,
    private val participantRepository: ParticipantRepository,
    private val random: Random = Random.Default,
) {
    @Throws(Exception::class)
    suspend fun tossGroup(id: GroupId): List<Participant> {
        val group = groupRepository.read(id)
        checkNotNull(group) { "Group by provided id $id does not exist" }
        val participants = group.participants
        if (participants.size < 3) {
            throw Exception()
        }

        val recipients = mutableSetOf<ParticipantId>()
        val participantsAfterToss = participants.map { participant ->
            var recipient: Recipient
            while (true) {
                recipient = participants.random(random).toRecipient()
                if (recipient.id in recipients || recipient.id == participant.id) {
                    continue
                }
                recipients.add(recipient.id)
                participantRepository.update(id, participant.id, recipient.id)
                break
            }
            Participant(
                id = participant.id,
                name = participant.name,
                wish = participant.wish,
                recipient = recipient,
            )
        }
        return participantsAfterToss
    }

    class Exception : IllegalStateException("Count of participants must be greater or equal 3")
}
