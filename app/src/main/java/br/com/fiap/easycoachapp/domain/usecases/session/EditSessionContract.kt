package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity

interface EditSessionContract {
    fun execute(session: SessionEntity,
                specialty: SpecialtyEntity,
                coachee: CoacheeEntity
    )
}