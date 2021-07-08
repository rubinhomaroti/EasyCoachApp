package br.com.fiap.easycoachapp.domain.usecases.coach

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity

interface SendInvitationContract {
    fun execute(fromCoach: CoachEntity,
                toCoachee: CoacheeEntity,
                sessionInvitation: SessionEntity)
}