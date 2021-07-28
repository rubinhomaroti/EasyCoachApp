package br.com.fiap.easycoachapp.viewModel.login

import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.helpers.IdGenerator
import br.com.fiap.easycoachapp.domain.helpers.UserType
import br.com.fiap.easycoachapp.domain.usecases.login.CheckUserIsAuthenticatedContract
import br.com.fiap.easycoachapp.domain.usecases.login.DoLoginContract
import br.com.fiap.easycoachapp.domain.usecases.signUp.DoSignUpContract
import java.util.*

class LoginViewModel(
    private val contract: LoginContract,
    private val checkUserIsAuthenticated: CheckUserIsAuthenticatedContract,
    private val doLogin: DoLoginContract,
    private val doSignUp: DoSignUpContract
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
        doSignUp.execute(
            CoachEntity(
            uid = IdGenerator().generate(),
            email = "teste2@fiap.com.br",
            password = "123456",
            name = "Teste 2",
            birthDate = Date(),
            sex = "M",
            cpf = "11111111111",
            contactNumber = null,
            cancellationFee = 0.1,
            cnpj = null,
            coachees = null,
            sessionPackages = null,
            sessions = null,
            specialties = null),
            UserType.COACH,
            {}, {}
        )
        contract.goToSignUpActivity()
    }

    fun onForgotPasswordPressed() {
        contract.goToForgotPassword()
    }
}