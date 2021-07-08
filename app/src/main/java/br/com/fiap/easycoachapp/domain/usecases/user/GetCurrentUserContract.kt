package br.com.fiap.easycoachapp.domain.usecases.user

import br.com.fiap.easycoachapp.domain.entities.UserEntity

interface GetCurrentUserContract {
    fun execute(): UserEntity
}