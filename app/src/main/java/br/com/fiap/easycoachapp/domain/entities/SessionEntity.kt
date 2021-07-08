package br.com.fiap.easycoachapp.domain.entities

import java.util.Date

class SessionEntity (
    val id: Int,
    val scheduledDateTimeStart: Date,
    val scheduledDateTimeEnd: Date,
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