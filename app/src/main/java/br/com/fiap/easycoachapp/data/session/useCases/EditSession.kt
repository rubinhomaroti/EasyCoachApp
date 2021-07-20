package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.session.EditSessionContract
import com.google.firebase.firestore.FirebaseFirestore

class EditSession (
    private val db: FirebaseFirestore
    ) : EditSessionContract {
    override fun execute(
        session: SessionEntity
    ) {
        db.collection("sessions")
            .whereEqualTo("uid",session.uid)
            .get()
            .addOnSuccessListener { documents ->
                val updateSession = documents.firstOrNull()
                updateSession?.reference?.set(session)
            }
    }
}