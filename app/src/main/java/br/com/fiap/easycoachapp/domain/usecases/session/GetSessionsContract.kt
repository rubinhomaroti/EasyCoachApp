package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError

interface GetSessionsContract {
    fun execute(coach: CoachEntity,
                onResult: (ArrayList<SessionEntity>) -> Unit,
                onFailure: (DomainError) -> Unit)
}