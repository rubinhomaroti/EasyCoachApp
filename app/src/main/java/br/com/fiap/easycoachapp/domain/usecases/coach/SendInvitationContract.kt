package br.com.fiap.easycoachapp.domain.usecases.coach

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.coachee.model.CoacheeModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel

interface SendInvitationContract {
    fun execute(fromCoach: CoachModel,
                toCoachee: CoacheeModel,
                sessionInvitation: SessionModel)
}