package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.coachee.model.CoacheeModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel
import br.com.fiap.easycoachapp.data.sessionPackage.model.SessionPackageModel
import br.com.fiap.easycoachapp.data.specialty.model.SpecialtyModel

interface CreateSessionContract {
    fun execute(session: SessionModel,
                sessionPackage: SessionPackageModel,
                specialty: SpecialtyModel,
                coach: CoachModel,
                coachee: CoacheeModel)
}