package br.com.fiap.easycoachapp.domain.entities

class SpecialtyEntity (
    val uid: String,
    val description: String,
    val coachs: ArrayList<CoachEntity>?,
    val sessions: ArrayList<SessionEntity>?
)