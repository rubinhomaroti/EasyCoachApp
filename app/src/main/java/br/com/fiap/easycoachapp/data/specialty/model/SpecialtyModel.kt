package br.com.fiap.easycoachapp.data.specialty.model

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel

class SpecialtyModel(
    val id: String,
    val description: String,
    val coachs: ArrayList<CoachModel>?,
    val sessions: ArrayList<SessionModel>?
)