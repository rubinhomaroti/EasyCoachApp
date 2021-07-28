package br.com.fiap.easycoachapp.viewModel.signUp

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.UserEntity
import br.com.fiap.easycoachapp.domain.helpers.UserType
import br.com.fiap.easycoachapp.domain.usecases.signUp.DoSignUpContract
import br.com.fiap.easycoachapp.viewModel.BaseViewModel
import java.util.Date

class SignUpViewModel(
    private val contract: SignUpContract,
    private val doSignUp: DoSignUpContract
) : BaseViewModel() {

    var name: String = ""
    var birthDate: Date = Date()
    var sex: String = "M"
    var cpf: String = ""
    var contactNumber: String = ""
    var email: String = ""
    var password: String = ""
    var userType: UserType = UserType.COACH

    fun onSignUpPressed() {
        val user: UserEntity

        when (this.userType) {
            UserType.COACH -> user = CoachEntity(
                "",
                name,
                birthDate,
                sex,
                cpf,
                contactNumber,
                email,
                password,
                null,
                0.1,
                null,
                null,
                null,
                null
            )
            UserType.COACHEE -> user = CoacheeEntity(
                "",
                name,
                birthDate,
                sex,
                cpf,
                contactNumber,
                email,
                password,
                null,
                null
            )
        }
        doSignUp.execute(
            user,
            userType,
            {contract.goToHomeActivity()},
            {contract.showErrorMessage()})
    }
}