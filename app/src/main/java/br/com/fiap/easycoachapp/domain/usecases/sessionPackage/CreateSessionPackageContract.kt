package br.com.fiap.easycoachapp.domain.usecases.sessionPackage

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.sessionPackage.model.SessionPackageModel

interface CreateSessionPackageContract {
    fun execute(sessionPackage: SessionPackageModel,
                coach: CoachModel)
}