package br.com.fiap.easycoachapp.domain.usecases.specialty

import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity

interface GetSpecialtiesContract {
    fun execute(onResult: (ArrayList<SpecialtyEntity>) -> Unit)
}