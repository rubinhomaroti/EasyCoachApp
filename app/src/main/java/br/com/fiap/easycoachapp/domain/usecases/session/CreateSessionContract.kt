package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.helpers.DomainError

interface CreateSessionContract {
    fun execute(
        fromCoach: CoachEntity,
        toCoachee: CoacheeEntity,
        sessionInvitation: SessionEntity,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit)
}