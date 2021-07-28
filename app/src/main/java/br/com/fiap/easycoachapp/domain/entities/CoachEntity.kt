package br.com.fiap.easycoachapp.domain.entities

import com.google.firebase.Timestamp
import java.util.Date
import kotlin.collections.ArrayList

class CoachEntity (
    uid: String,
    name: String,
    birthDate: Date,
    sex: String,
    cpf: String,
    contactNumber: String?,
    email: String,
    password: String?,
    val cnpj: String?,
    val cancellationFee: Double,
    val specialties: ArrayList<SpecialtyEntity>?,
    val coachees: ArrayList<CoacheeEntity>?
) : UserEntity(uid, name, birthDate, sex, cpf, contactNumber, email, password) {
    companion object {
        fun fromJson(json: MutableMap<String, Any>) : CoachEntity {
            return CoachEntity(
                uid = json["uid"].toString(),
                name = json["name"].toString(),
                birthDate = (json["birthDate"] as Timestamp).toDate(),
                sex = json["sex"].toString(),
                cpf = json["cpf"].toString(),
                contactNumber = json["contactNumber"]?.toString(),
                email = json["email"].toString(),
                password = json["password"]?.toString(),
                cnpj = json["cnpj"]?.toString(),
                cancellationFee = json["cancellationFee"] as Double,
                specialties = json["specialties"] as ArrayList<SpecialtyEntity>?,
                coachees = json["coachees"] as ArrayList<CoacheeEntity>?
            )
        }
    }
}