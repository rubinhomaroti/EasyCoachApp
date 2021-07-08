package br.com.fiap.easycoachapp.domain.usecases.login

import br.com.fiap.easycoachapp.domain.helpers.DomainError

interface DoLoginContract {
    fun execute(email: String,
                password: String,
                onSuccessListener: () -> Unit,
                onFailureListener: (DomainError) -> Unit)
}