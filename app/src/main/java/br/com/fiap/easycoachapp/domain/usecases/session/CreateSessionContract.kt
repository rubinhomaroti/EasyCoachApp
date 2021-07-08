package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.domain.entities.*

interface CreateSessionContract {
    fun execute(session: SessionEntity,
                sessionPackage: SessionPackageEntity,
                specialty: SpecialtyEntity,
                coach: CoachEntity,
                coachee: CoacheeEntity)
}