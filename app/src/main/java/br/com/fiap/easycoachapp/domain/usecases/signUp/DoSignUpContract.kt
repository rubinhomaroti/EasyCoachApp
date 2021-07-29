package br.com.fiap.easycoachapp.domain.usecases.signUp

import br.com.fiap.easycoachapp.domain.entities.UserEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError

interface DoSignUpContract {
    fun execute(user: UserEntity,
                onSuccessListener: () -> Unit,
                onFailureListener: (DomainError) -> Unit)
}