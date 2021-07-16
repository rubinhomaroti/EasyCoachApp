package br.com.fiap.easycoachapp.domain.usecases.specialty

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError

interface GetSpecialtiesContract {
    fun execute(
                onResult: (ArrayList<SpecialtyEntity>) -> Unit,
                onFailure: (DomainError) -> Unit)
}