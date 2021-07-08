package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity

interface GetSessionsContract {
    fun execute(coach: CoachEntity,
                onResult: (ArrayList<SessionEntity>) -> Unit)
}