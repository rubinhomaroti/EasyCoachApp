package br.com.fiap.easycoachapp.domain.usecases.specialty

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity

interface GetSpecialtiesContract {
    fun execute(coach: CoachEntity,
                onResult: (ArrayList<SpecialtyEntity>) -> Unit)
}