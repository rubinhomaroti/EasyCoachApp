package br.com.fiap.easycoachapp.domain.usecases.login

interface CheckUserIsAuthenticatedContract {
    fun execute(): Boolean
}