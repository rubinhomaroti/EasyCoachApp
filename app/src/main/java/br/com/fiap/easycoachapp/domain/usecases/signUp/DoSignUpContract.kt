package br.com.fiap.easycoachapp.domain.usecases.signUp

import br.com.fiap.easycoachapp.data.user.model.UserModel
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.helpers.UserType

interface DoSignUpContract {
    fun execute(user: UserModel,
                type: UserType,
                onSuccessListener: () -> Unit,
                onFailureListener: (DomainError) -> Unit)
}