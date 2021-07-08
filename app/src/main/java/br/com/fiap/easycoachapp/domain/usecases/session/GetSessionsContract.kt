package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel

interface GetSessionsContract {
    fun execute(coach: CoachModel,
                onResult: (ArrayList<SessionModel>) -> Unit)
}