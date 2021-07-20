package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.session.CreateSessionContract
import com.google.firebase.firestore.FirebaseFirestore


class CreateSession (
    private val db: FirebaseFirestore
) : CreateSessionContract {
    override fun execute(
        session: SessionEntity,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        db.collection("sessions")
            .add(session)
            .addOnSuccessListener { onSuccessListener() }
            .addOnFailureListener { onFailureListener(DomainError.FIRESTORE_ERROR) }
    }
}