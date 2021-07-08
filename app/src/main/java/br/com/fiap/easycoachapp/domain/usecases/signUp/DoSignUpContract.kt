package br.com.fiap.easycoachapp.domain.usecases.signUp

import br.com.fiap.easycoachapp.domain.entities.UserEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.helpers.UserType

interface DoSignUpContract {
    fun execute(user: UserEntity,
                type: UserType,
                onSuccessListener: () -> Unit,
                onFailureListener: (DomainError) -> Unit)
}