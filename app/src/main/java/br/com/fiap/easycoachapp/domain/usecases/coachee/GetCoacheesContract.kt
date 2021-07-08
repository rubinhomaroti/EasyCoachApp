package br.com.fiap.easycoachapp.domain.usecases.coachee

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.coachee.model.CoacheeModel

interface GetCoacheesContract {
    fun execute(coach: CoachModel, onResult: (ArrayList<CoacheeModel>) -> Unit)
}