package br.com.fiap.easycoachapp.domain.entities

import com.google.firebase.Timestamp
import java.util.Date
import kotlin.collections.ArrayList

class CoacheeEntity (
    uid: String,
    name: String,
    birthDate: Date,
    sex: String,
    cpf: String,
    contactNumber: String?,
    email: String,
    password: String?,
    val sessions: ArrayList<SessionEntity>?,
) : UserEntity(uid, name, birthDate, sex, cpf, contactNumber, email, password) {

    var coach: CoachEntity? = null

    companion object {
        fun fromJson(json: MutableMap<String, Any>) : CoacheeEntity {
            return CoacheeEntity(
                uid = json["uid"].toString(),
                name = json["name"].toString(),
                birthDate = (json["birthDate"] as Timestamp).toDate(),
                sex = json["sex"].toString(),
                cpf = json["cpf"].toString(),
                contactNumber = json["contactNumber"]?.toString(),
                email = json["email"].toString(),
                password = json["password"]?.toString(),
                sessions = getSessionsFromJson(json["sessions"])
            )
        }

        private fun getSessionsFromJson(json: Any?) : ArrayList<SessionEntity> {
            val array = json as ArrayList<*>
            val sessions = ArrayList<SessionEntity>()
            array.forEach {
                if (it is HashMap<*, *>) {
                    val hashMap = it as MutableMap<String, Any>
                    sessions.add(SessionEntity.fromJson(hashMap))
                }
            }
            return sessions
        }
    }

    override fun toString(): String {
        return name
    }
}