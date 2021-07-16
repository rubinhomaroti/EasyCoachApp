package br.com.fiap.easycoachapp.data.specialty.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.entities.SpecialtyEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.specialty.GetSpecialtiesContract
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.lang.Exception

class GetSpecialties (
    private val db: FirebaseFirestore
    ) : GetSpecialtiesContract {
        override fun execute(
            onResult: (ArrayList<SpecialtyEntity>) -> Unit,
            onFailure: (DomainError) -> Unit
        ) {
            db.collection("specialties")
                .get()
                .addOnSuccessListener { documents ->
                    try {
                        val specialtieList = arrayListOf<SpecialtyEntity>()
                        for (specialties in documents!!) {
                            val gson = Gson()
                            val jsonDataSnapshot = gson.toJson(specialties.data)
                            val specialtieEntity =
                                gson.fromJson(jsonDataSnapshot, SpecialtyEntity::class.java)
                            specialtieList.add(specialtieEntity)
                        }
                        onResult(specialtieList)
                    } catch (e: Exception) {
                        onFailure(DomainError.FIRESTORE_ERROR)
                    }
                }
        }
    }