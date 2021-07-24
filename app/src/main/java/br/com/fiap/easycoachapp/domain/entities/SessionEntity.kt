package br.com.fiap.easycoachapp.domain.entities

import java.util.Date

class SessionEntity (
    val uid: String,
    val scheduledDateTime: Date,
    val sessionNumber: Int,
    val title: String,
    val description: String?,
    val inviteUrl: String?,
    val hasCancellationFee: Boolean,
    val coach: CoachEntity,
    val coachee: CoacheeEntity,
    val specialty: SpecialtyEntity,
    val sessionPackage: SessionPackageEntity
)