package br.com.fiap.easycoachapp.domain.usecases.sessionPackage

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.sessionPackage.model.SessionPackageModel

interface GetSessionPackagesContract {
    fun execute(coach: CoachModel,
                onResult: (ArrayList<SessionPackageModel>) -> Unit)
}