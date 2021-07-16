package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.session.GetSessionsContract
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.lang.Exception

class GetSessions (
    private val db: FirebaseFirestore
    ) : GetSessionsContract {
    override fun execute(
        coach: CoachEntity,
        onResult: (ArrayList<SessionEntity>) -> Unit,
        onFailure: (DomainError) -> Unit
    ) {
        db.collection("sessions").whereEqualTo("coach", coach)
            .get()
            .addOnSuccessListener { documents ->
                try {
                    val sessionList = arrayListOf<SessionEntity>()
                    for (session in documents!!) {
                        val gson = Gson()
                        val jsonDataSnapshot = gson.toJson(session.data)
                        val sessionEntity =
                            gson.fromJson(jsonDataSnapshot, SessionEntity::class.java)
                        sessionList.add(sessionEntity)
                    }
                    onResult(sessionList)
                } catch (e: Exception) {
                    onFailure(DomainError.FIRESTORE_ERROR)
                }
            }
    }
}