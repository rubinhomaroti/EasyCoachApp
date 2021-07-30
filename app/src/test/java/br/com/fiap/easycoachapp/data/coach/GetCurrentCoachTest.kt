package br.com.fiap.easycoachapp.data.coach

import br.com.fiap.easycoachapp.data.coach.useCases.GetCurrentCoach
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetCurrentCoachTest {

    private var sut: GetCurrentCoach? = null
    private val firebaseAuth: FirebaseAuth = Mockito.mock(FirebaseAuth::class.java)
    private val firebaseUser: FirebaseUser = Mockito.mock(FirebaseUser::class.java)
    private val firestore: FirebaseFirestore = Mockito.mock(FirebaseFirestore::class.java)
    private val coachCollection: CollectionReference = Mockito.mock(CollectionReference::class.java)

    private fun mockFirebaseAuthFailure() {
        Mockito.`when`(firebaseAuth.currentUser).thenAnswer {
            return@thenAnswer null
        }
    }

    @Before
    fun setup() {
        sut = GetCurrentCoach(firebaseAuth, firestore)
    }

    @Test
    fun `Should not access firestore collection if user is not authenticated`() {
        mockFirebaseAuthFailure()
        sut?.execute({ }, { })
        Mockito.verify(coachCollection, times(0)).whereEqualTo("uid", firebaseUser.uid)
    }
}