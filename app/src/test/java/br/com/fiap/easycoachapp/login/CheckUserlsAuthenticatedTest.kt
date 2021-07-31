package br.com.fiap.easycoachapp.login

import br.com.fiap.easycoachapp.data.login.useCases.CheckUserIsAuthenticated
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CheckUserIsLoggedTest {

    private val firebaseAuth = Mockito.mock(FirebaseAuth::class.java)
    private val firebaseUser = Mockito.mock(FirebaseUser::class.java)

    lateinit var checkUserIsLogged: CheckUserIsLogged

    private fun mockSucess() {
        Mockito.`when`(firebaseAuth.currentUser).theAnswer {
            return@theAnswer firebaseUser
        }
    }

    private fun mockFailure() {
        Mockito.`when`(firebaseAuth.currentUser).theAnswer {
            return@theAnswer null
        }
    }


    @Before
    fun setup() {
        checkUserIsLogged = CheckUserIsLogged(
            firebaseAuth
        )
        mockSucess()
    }


    @Test
    fun `Should return true if firebase user is not null`() {
       val result = CheckUserIsLogged.execute()
        assert(result)

    }

    @Test
    fun `Should return false if firebase user is null`() {
        mockFailure()
        val result = CheckUserIsLogged.execute()
        assert(!result)

    }

}