package br.com.fiap.easycoachapp.data.sessionPackage.model

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel

class SessionPackageModel(
    val id: Int,
    val totalSessions: Int,
    val price: Double,
    val sessions: Collection<SessionModel>,
    val coach: CoachModel
)