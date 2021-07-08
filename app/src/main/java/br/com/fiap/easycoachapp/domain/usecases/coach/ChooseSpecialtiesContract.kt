package br.com.fiap.easycoachapp.domain.usecases.coach

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity

interface ChooseSpecialtiesContract {
    fun execute(coach: CoachEntity,
                specialties: ArrayList<SpecialtyEntity>)
}