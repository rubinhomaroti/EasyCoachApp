package br.com.fiap.easycoachapp.data.coachee.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.coachee.GetCoacheesContract
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.lang.Exception

class GetCoachees(
    private val db: FirebaseFirestore
) : GetCoacheesContract {
    override fun execute(
        coach: CoachEntity,
        onResult: (ArrayList<CoacheeEntity>) -> Unit,
        onFailure: (DomainError) -> Unit
    ) {
        db.collection("coachees").whereArrayContains("coachs", coach)
            .get()
            .addOnSuccessListener { documents ->
                try {
                    val coacheesList = arrayListOf<CoacheeEntity>()
                    for (coachee in documents!!) {
                        val gson = Gson()
                        val jsonDataSnapshot = gson.toJson(coachee.data)
                        val coacheeEntity =
                            gson.fromJson(jsonDataSnapshot, CoacheeEntity::class.java)
                        coacheesList.add(coacheeEntity)
                    }
                    onResult(coacheesList)
                } catch (e: Exception) {
                    onFailure(DomainError.FIRESTORE_ERROR)
                }
            }
    }
}