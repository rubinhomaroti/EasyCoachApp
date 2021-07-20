package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import com.google.firebase.firestore.FirebaseFirestore

class DeleteSession (
    private val db: FirebaseFirestore
) : DeleteSessionContract {
    override fun execute(
        session: SessionEntity
    ) {
        db.collection("sessions")
            .whereEqualTo("uid",session.uid)
            .get()
            .addOnSuccessListener { documents ->
                val deleteSession = documents.firstOrNull()
                deleteSession?.reference?.delete()
            }
    }
}