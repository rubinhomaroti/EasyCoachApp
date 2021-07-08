package br.com.fiap.easycoachapp.domain.usecases.coach

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.specialty.model.SpecialtyModel

interface ChooseSpecialtiesContract {
    fun execute(coach: CoachModel,
                specialties: ArrayList<SpecialtyModel>)
}