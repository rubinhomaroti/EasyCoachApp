package br.com.fiap.easycoachapp.domain.entities

class SessionPackageEntity (
    val uid: String,
    val totalSessions: Int,
    val price: Double,
    val sessions: Collection<SessionEntity>,
    val coach: CoachEntity
)