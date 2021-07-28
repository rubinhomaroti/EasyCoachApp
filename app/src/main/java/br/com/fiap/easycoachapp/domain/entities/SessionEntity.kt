package br.com.fiap.easycoachapp.domain.entities

import com.google.firebase.Timestamp
import java.util.Date

class SessionEntity (
    val uid: String,
    val scheduledDateTime: Date,
    val sessionNumber: Int,
    val title: String,
    val description: String?,
    val inviteUrl: String?,
    val hasCancellationFee: Boolean,
    val specialtyUid: String
) {
    companion object {
        fun fromJson(json: MutableMap<String, Any>) : SessionEntity {
            return SessionEntity(
                uid = json["uid"].toString(),
                scheduledDateTime = (json["scheduledDateTime"] as Timestamp).toDate(),
                sessionNumber = json["sessionNumber"] as Int,
                title = json["title"].toString(),
                description = json["description"]?.toString(),
                inviteUrl = json["inviteUrl"].toString(),
                hasCancellationFee = json["hasCancellationFee"] as Boolean,
                specialtyUid = json["specialtyUid"].toString()
            )
        }
    }
}