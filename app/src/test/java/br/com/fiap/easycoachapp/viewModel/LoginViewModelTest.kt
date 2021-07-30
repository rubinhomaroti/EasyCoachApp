package br.com.fiap.easycoachapp.viewModel

import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.login.CheckUserIsAuthenticatedContract
import br.com.fiap.easycoachapp.domain.usecases.login.DoLoginContract
import br.com.fiap.easycoachapp.viewModel.login.LoginContract
import br.com.fiap.easycoachapp.viewModel.login.LoginViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {
    var sut: LoginViewModel? = null

    private val contract = Mockito.mock(LoginContract::class.java)
    private val checkUserIsAuthenticated = Mockito.mock(CheckUserIsAuthenticatedContract::class.java)
    private val doLogin = Mockito.mock(DoLoginContract::class.java)

    @Before
    fun setup() {
        sut = LoginViewModel(
            contract,
            checkUserIsAuthenticated,
            doLogin
        )
    }

    @Test
    fun `Should call go to home activity if user is logged`() {
        Mockito.`when`(checkUserIsAuthenticated.execute()).thenAnswer {
            return@thenAnswer true
        }

        sut?.onCreate()

        Mockito.verify(contract, times(1)).goToHomeActivity()
    }

    @Test
    fun `Should not call go to home activity if user is logged`() {
        Mockito.`when`(checkUserIsAuthenticated.execute()).thenAnswer {
            return@thenAnswer false
        }

        sut?.onCreate()

        Mockito.verify(contract, times(0)).goToHomeActivity()
    }

    @Test
    fun `Should call do login with correct parameters when button is pressed`() {
        sut?.email = "teste@fiap.com.br"
        sut?.password = "123456"

        sut?.onLoginPressed()

        Mockito.verify(doLogin, times(1)).execute(eq("teste@fiap.com.br"), eq("123456"), any(), any())
    }

    @Test
    fun `Should call go to home activity if make login returns success`() {
        Mockito.`when`(doLogin.execute(any(), any(), any(), any())).then { invocation ->
            (invocation.arguments[2] as () -> Unit).invoke()
        }

        sut?.onLoginPressed()

        Mockito.verify(contract, times(1)).goToHomeActivity()
    }

    @Test
    fun `Should call show generic error if make login returns failure`() {

        Mockito.`when`(doLogin.execute(any(), any(), any(), any())).then { invocation ->
            (invocation.arguments[3] as (DomainError) -> Unit).invoke(DomainError.AUTH_ERROR)
        }

        sut?.onLoginPressed()

        Mockito.verify(contract, times(1)).showErrorMessage()
    }
}