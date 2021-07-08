package br.com.fiap.easycoachapp.domain.usecases.user

import br.com.fiap.easycoachapp.data.user.model.UserModel

interface GetCurrentUserContract {
    fun execute(): UserModel
}