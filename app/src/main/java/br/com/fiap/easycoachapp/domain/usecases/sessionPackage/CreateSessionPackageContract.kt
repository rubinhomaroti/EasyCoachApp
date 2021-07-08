package br.com.fiap.easycoachapp.domain.usecases.sessionPackage

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SessionPackageEntity

interface CreateSessionPackageContract {
    fun execute(sessionPackage: SessionPackageEntity,
                coach: CoachEntity)
}