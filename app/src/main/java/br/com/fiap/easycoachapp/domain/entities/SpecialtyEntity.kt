package br.com.fiap.easycoachapp.domain.entities

class SpecialtyEntity (
    val id: String,
    val description: String,
    val coachs: ArrayList<CoachEntity>?,
    val sessions: ArrayList<SessionEntity>?
)