package br.com.fiap.easycoachapp.data.session.model

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.coachee.model.CoacheeModel
import br.com.fiap.easycoachapp.data.sessionPackage.model.SessionPackageModel
import br.com.fiap.easycoachapp.data.specialty.model.SpecialtyModel
import java.util.Date

class SessionModel (
    val id: Int,
    val scheduledDateTimeStart: Date,
    val scheduledDateTimeEnd: Date,
    val sessionNumber: Int,
    val title: String,
    val description: String?,
    val inviteUrl: String?,
    val hasCancellationFee: Boolean,
    val coach: CoachModel,
    val coachee: CoacheeModel,
    val specialty: SpecialtyModel,
    val sessionPackage: SessionPackageModel
)