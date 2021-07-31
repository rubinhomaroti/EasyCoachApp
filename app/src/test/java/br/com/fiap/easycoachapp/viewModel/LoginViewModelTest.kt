package br.com.fiap.easycoachapp.viewModel

import br.com.fiap.easycoachapp.domain.usecases.login.CheckUserIsAuthenticatedContract
import br.com.fiap.easycoachapp.login.CheckUserIsLoggedTest
import br.com.fiap.easycoachapp.viewModel.login.LoginContract
import br.com.fiap.easycoachapp.viewModel.login.LoginViewModel
import com.google.common.base.CharMatcher.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {

    companion object{
        const val EMAIL = "teste@fiap.com.br"
        const val PASSWORD = "123456"
    }

    lateinit var loginViewModel: LoginViewModel
    private val contract = Mockito.mock(LoginContract::class.java)
    private val checkUserIsLogged = Mockito.mock(CheckUserIsLoggedContract::class.java)
    private val makeLogin = Mockito.mock(MakeLoginContract::class.java)

    @Before
    fun setup(){
        loginViewModel = LoginViewModel(
            contract,
            checkUserIsLogged,
            makeLogin

        )
    }

    @Test
    fun `Should call go to main activity if user is logged`(){
        Mockito.`when`(checkUserIsLogged.execute()).thenAnswer{return@thenAnswer true}

        loginViewModel.onCreate()

        Mockito.verify(contract, times(1)).goToHomeActivity()

    }

    @Test
    fun `Should not call go to main activity if user is not logged`(){
        Mockito.`when`(checkUserIsLogged.execute()).thenAnswer{return@thenAnswer false}

        loginViewModel.onCreate()

        Mockito.verify(contract, times(0)).goToHomeActivity()

    }

    @Test
    fun `Should call make login with parameters when button is pressed`(){
    loginViewModel.email = EMAIL
    loginViewModel.password = PASSWORD

     loginViewModel.onLoginPressed()

      Mockito.verify(makeLogin, times(1)).execute(eq(EMAIL), eq(PASSWORD), any(),any())

    }


}