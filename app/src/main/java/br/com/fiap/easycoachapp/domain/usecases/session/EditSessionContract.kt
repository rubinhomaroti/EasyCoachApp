package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.data.coachee.model.CoacheeModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel
import br.com.fiap.easycoachapp.data.specialty.model.SpecialtyModel

interface EditSessionContract {
    fun execute(session: SessionModel,
                specialty: SpecialtyModel,
                coachee: CoacheeModel
    )
}