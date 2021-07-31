package br.com.fiap.easycoachapp.login

import android.app.Activity
import br.com.fiap.easycoachapp.data.login.useCases.DoLogin
import com.google.android.gms.common.api.internal.TaskApiCall
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.lang.Exception
import java.util.concurrent.Executor
import javax.security.auth.callback.PasswordCallback

class DoLoginTest {

    companion object{
        const val EMAIL = "teste@fiap.com.br"
        const val PASSWORD = "123456"
    }

    lateinit var dologin: DoLogin
    private val firebaseAuth = Mockito.mock(FirebaseAuth::class.java)
    private val authResult = Mockito.mock(AuthResult::class.java)
    private lateinit var task: Task<AuthResult>
    private fun  mockSingIn(){
        Mockito.`when`(firebaseAuth.signInWithEmailAndPassword(EMAIL, PASSWORD))
            .thenReturn(task)
    }


    @Before
    fun setup(){
        dologin = DoLogin(
        firebaseAuth
        )
        task = object : Task<AuthResult>(){
            override fun isCanceled(): Boolean {
              return true
            }

            override fun getException(): Exception? {
               return null
            }

            override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
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
                p0.onSuccess(authResult)
                return task
            }

            override fun addOnSuccessListener(
                p0: Activity,
                p1: OnSuccessListener<in AuthResult>
            ): Task<AuthResult> {
                p0.onSuccess(authResult)
                return task
            }

            override fun isComplete(): Boolean {
                return false
            }

            override fun isSuccessful(): Boolean {
                return true
            }

        }

        mockSingIn()
    }

    @Test
    fun `Should call singIn with correct values`(){
        dologin.execute(EMAIL,PASSWORD, {},{})

        Mockito.verify(firebaseAuth, times(1)).signInWithEmailAndPassword(EMAIL, PASSWORD)
    }
}