package br.com.fiap.easycoachapp.domain.usecases.coachee

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity

interface GetCoacheesContract {
    fun execute(coach: CoachEntity, onResult: (ArrayList<CoacheeEntity>) -> Unit)
}