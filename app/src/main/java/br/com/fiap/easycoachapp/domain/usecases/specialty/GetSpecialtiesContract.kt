package br.com.fiap.easycoachapp.domain.usecases.specialty

import br.com.fiap.easycoachapp.data.specialty.model.SpecialtyModel

interface GetSpecialtiesContract {
    fun execute(onResult: (ArrayList<SpecialtyModel>) -> Unit)
}