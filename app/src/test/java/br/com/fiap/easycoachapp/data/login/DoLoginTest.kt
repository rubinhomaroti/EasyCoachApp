package br.com.fiap.easycoachapp.data.login

import android.app.Activity
import br.com.fiap.easycoachapp.data.login.useCases.DoLogin
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class DoLoginTest {
    companion object {
        const val FAKE_EMAIL = "teste@fiap.com.br"
        const val FAKE_PASSWORD = "123456"
    }

    var sut: DoLogin? = null
    private val firebaseAuth = Mockito.mock(FirebaseAuth::class.java)
    private val authResult = Mockito.mock(AuthResult::class.java)

    private lateinit var task: Task<AuthResult>

    private fun mockSignIn() {
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(FAKE_EMAIL, FAKE_PASSWORD))
            .thenReturn(task)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        task = object : Task<AuthResult>() {
            override fun isComplete(): Boolean = true
            override fun isSuccessful(): Boolean = true

            override fun getException(): java.lang.Exception {
                return java.lang.Exception()
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
                p0.onFailure(Exception())
                return task
            }

            override fun addOnFailureListener(
                p0: Executor,
                p1: OnFailureListener
            ): Task<AuthResult> {
                p1.onFailure(Exception())
                return task
            }

            override fun addOnFailureListener(
                p0: Activity,
                p1: OnFailureListener
            ): Task<AuthResult> {
                p1.onFailure(Exception())
                return task
            }

            override fun getResult(): AuthResult? {
                return authResult
            }

            override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult? {
                return authResult
            }

            override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
                p0.onSuccess(authResult)
                return task
            }

            override fun addOnSuccessListener(
                p0: Executor,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                p1.onSuccess(authResult)
                return task
            }

            override fun addOnSuccessListener(
                p0: Activity,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                p1.onSuccess(authResult)
                return task
            }

            override fun isCanceled(): Boolean {
                return true
            }
        }

        sut = DoLogin(firebaseAuth)
        mockSignIn()
    }

    @Test
    fun `Should call signIn with correct values`() {
        sut?.execute(FAKE_EMAIL, FAKE_PASSWORD, { }, { })

        Mockito.verify(firebaseAuth, times(1)).signInWithEmailAndPassword(FAKE_EMAIL, FAKE_PASSWORD)
    }

    @Test
    fun `Should not call signIn with empty e-mail or password`() {
        sut?.execute("", "", { }, { })

        Mockito.verify(firebaseAuth, times(0)).signInWithEmailAndPassword(FAKE_EMAIL, FAKE_PASSWORD)
    }
}