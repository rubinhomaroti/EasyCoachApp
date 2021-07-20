package br.com.fiap.easycoachapp.viewModel.login

import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.usecases.login.CheckUserIsAuthenticatedContract
import br.com.fiap.easycoachapp.domain.usecases.login.DoLoginContract

class LoginViewModel(
    private val contract: LoginContract,
    private val checkUserIsAuthenticated: CheckUserIsAuthenticatedContract,
    private val doLogin: DoLoginContract
) : ViewModel() {
    var email: String = ""
    var password: String = ""

    fun onCreate() {
        if (checkUserIsAuthenticated.execute()) {
            contract.goToHomeActivity()
        }
    }

    fun onLoginPressed() {
        doLogin.execute(
            email,
            password,
            {contract.goToHomeActivity()},
            {contract.showErrorMessage()})
    }

    fun onSignUpPressed() {
        contract.goToSignUpActivity()
    }
}