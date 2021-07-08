package br.com.fiap.easycoachapp.domain.entities

class SessionPackageEntity (
    val id: Int,
    val totalSessions: Int,
    val price: Double,
    val sessions: Collection<SessionEntity>,
    val coach: CoachEntity
)