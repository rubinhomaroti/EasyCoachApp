package br.com.fiap.easycoachapp.data.coach.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.coach.SendInvitationContract
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class SendInvitation (
    private val db: FirebaseFirestore
    ) : SendInvitationContract {
    override fun execute(
        fromCoach: CoachEntity,
        toCoachee: CoacheeEntity,
        sessionInvitation: SessionEntity
    ) {
        db.collection("coachs")
            .whereEqualTo("uid", fromCoach.uid)
            .get()
            .addOnSuccessListener { documents ->
                val coach = documents.firstOrNull()
                if (coach != null) {
                    val gson = Gson()
                    val jsonDataSnapshot = gson.toJson(coach.data)
                    val coachEntity = gson.fromJson(jsonDataSnapshot, CoachEntity::class.java)
                    coachEntity.sessions?.add(sessionInvitation)
                    coach.reference.set(coachEntity)
                }

                db.collection("coachees")
                    .whereEqualTo("uid", toCoachee.uid)
                    .get()
                    .addOnSuccessListener { documents ->
                        val coachee = documents.firstOrNull()
                        if (coachee != null) {
                            val gson = Gson()
                            val jsonDataSnapshot = gson.toJson(coachee.data)
                            val coacheeEntity =
                                gson.fromJson(jsonDataSnapshot, CoacheeEntity::class.java)
                            coacheeEntity.sessions?.add(sessionInvitation)
                            coachee.reference.set(coacheeEntity)
                        }
                    }
            }
    }
}

