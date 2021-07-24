package br.com.fiap.easycoachapp.main.di

import br.com.fiap.easycoachapp.data.coach.useCases.GetCurrentCoach
import br.com.fiap.easycoachapp.data.login.useCases.CheckUserIsAuthenticated
import br.com.fiap.easycoachapp.data.login.useCases.DoLogin
import br.com.fiap.easycoachapp.data.session.useCases.DeleteSession
import br.com.fiap.easycoachapp.data.session.useCases.GetSessions
import br.com.fiap.easycoachapp.data.signUp.useCases.DoSignUp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

object DataModules {
    val modules = module {
        factory { CheckUserIsAuthenticated(FirebaseAuth.getInstance()) }
        factory { DoLogin(FirebaseAuth.getInstance()) }
        factory { DoSignUp(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance()) }
        factory { GetCurrentCoach(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance()) }
        factory { GetSessions(FirebaseFirestore.getInstance()) }
        factory { DeleteSession(FirebaseFirestore.getInstance()) }
    }
}