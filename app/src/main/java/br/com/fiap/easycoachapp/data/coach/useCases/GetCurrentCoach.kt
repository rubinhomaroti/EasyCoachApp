package br.com.fiap.easycoachapp.data.coach.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class GetCurrentCoach(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : GetCurrentCoachContract {
    override fun execute(onResult: (CoachEntity) -> Unit,
                         onFailure: (DomainError) -> Unit) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("coachs")
                .whereEqualTo("uid", currentUser.uid)
                .get()
                .addOnSuccessListener { documents ->
                    val coach = documents.firstOrNull()
                    if (coach != null) {
                        val gson = Gson()
                        val jsonDataSnapshot = gson.toJson(coach.data)
                        val coachEntity = gson.fromJson(jsonDataSnapshot, CoachEntity::class.java)
                        onResult(coachEntity)
                    } else {
                        onFailure(DomainError.AUTH_ERROR)
                    }
                }

        }
        onFailure(DomainError.AUTH_ERROR)
    }
}