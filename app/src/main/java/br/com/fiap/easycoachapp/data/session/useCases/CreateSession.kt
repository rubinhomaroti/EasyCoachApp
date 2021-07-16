package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.usecases.session.CreateSessionContract
import com.google.firebase.firestore.FirebaseFirestore


class CreateSession (
    private val db: FirebaseFirestore
) : CreateSessionContract {
    override fun execute(
        session: SessionEntity) {
        db.collection("sessions").add(session)
    }
}