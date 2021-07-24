ackage br.com.fiap.easycoachapp.data.coach.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.coach.ChooseSpecialtiesContract
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class ChooseSpecialties (
    private val db: FirebaseFirestore
        ): ChooseSpecialtiesContract {
    override fun execute(
        coach: CoachEntity,
        specialties: ArrayList<SpecialtyEntity>
    ) {
        db.collection("coachs")
            .whereEqualTo("uid", coach.uid)
            .get()
            .addOnSuccessListener { documents ->
                val coachFirestore = documents.firstOrNull()
                coachFirestore?.reference?.update("specialties", specialties)
            }
    }
}