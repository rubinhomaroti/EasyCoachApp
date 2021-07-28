package br.com.fiap.easycoachapp.data.coach.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.coach.SendInvitationContract
import com.google.firebase.firestore.FirebaseFirestore

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
            .addOnSuccessListener { coachDocuments ->
                val coach = coachDocuments.firstOrNull()
                if (coach != null) {
                    val coachEntity = CoachEntity.fromJson(coach.data)
                    val coachee = coachEntity.coachees?.firstOrNull { c -> c.uid == toCoachee.uid }
                    if (coachee != null) {
                        coachEntity.coachees.remove(coachee)
                        coachee.sessions?.add(sessionInvitation)
                        coachEntity.coachees.add(coachee)
                        coach.reference.set(coachEntity)
                    }
                }
            }
    }
}

