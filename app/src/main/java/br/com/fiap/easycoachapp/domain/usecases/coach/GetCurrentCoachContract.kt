package br.com.fiap.easycoachapp.domain.usecases.coach

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError

interface GetCurrentCoachContract {
    fun execute(onResult: (CoachEntity) -> Unit,
                onFailure: (DomainError) -> Unit)
}