package br.com.fiap.easycoachapp.viewModel.login

import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity
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
        var sessions = ArrayList<SessionEntity>()
        sessions.add(
            SessionEntity(
                uid = IdGenerator().generate(),
                scheduledDateTime = Date(),
                sessionNumber = 1,
                title = "Rapport",
                description = "Deep diving nos três melhores e piores momentos da vida do Coachee",
                inviteUrl = "https://meet.google.com/xcv-jrwc-ira",
                hasCancellationFee = true,
                specialtyUid = "xx36DL08o0Mk5BCNLp7p"
            )
        )
        sessions.add(
            SessionEntity(
                uid = IdGenerator().generate(),
                scheduledDateTime = Date(),
                sessionNumber = 2,
                title = "Linguagem do amor",
                description = "Entender qual é a linguagem do amor em seus relacionamentos",
                inviteUrl = "https://meet.google.com/xcv-jrwc-ira",
                hasCancellationFee = true,
                specialtyUid = "xx36DL08o0Mk5BCNLp7p"
            )
        )
        sessions.add(
            SessionEntity(
                uid = IdGenerator().generate(),
                scheduledDateTime = Date(),
                sessionNumber = 3,
                title = "Crenças",
                description = "Entender as crenças limitantes em seus relacionamentos",
                inviteUrl = "https://meet.google.com/xcv-jrwc-ira",
                hasCancellationFee = true,
                specialtyUid = "xx36DL08o0Mk5BCNLp7p"
            )
        )

        var coachee = CoacheeEntity(
            uid = IdGenerator().generate(),
            name = "Mauricio",
            birthDate = Date(),
            sex = "M",
            cpf = "05906594051",
            contactNumber = "+5511991918585",
            email = "mauricio@fiap.com.br",
            password = null,
            sessions = sessions)

        var coachees = ArrayList<CoacheeEntity>()
        coachees.add(coachee)

        var specialty = SpecialtyEntity(
            uid = "xx36DL08o0Mk5BCNLp7p",
            description = "Amor"
        )

        var sps = ArrayList<SpecialtyEntity>()
        sps.add(specialty)

        doSignUp.execute(
            CoachEntity(
            uid = IdGenerator().generate(),
            email = "teste@fiap.com.br",
            password = "123456",
            name = "FIAP ON",
            birthDate = Date(),
            sex = "M",
            cpf = "69954983058",
            contactNumber = "+5511991918686",
            cancellationFee = 0.1,
            cnpj = "38134487000178",
            coachees = coachees,
            specialties = sps),
            {}, {}
        )
        contract.goToSignUpActivity()
    }

    fun onForgotPasswordPressed() {
        contract.goToForgotPassword()
    }
}