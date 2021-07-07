package br.com.fiap.easycoachapp.data.coachee.model

import br.com.fiap.easycoachapp.data.coach.model.CoachModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel
import br.com.fiap.easycoachapp.data.user.model.UserModel
import java.util.Date

class CoacheeModel(
    uid: String,
    name: String,
    birthDate: Date,
    sex: Char,
    cpf: String,
    contactNumber: String?,
    email: String,
    password: String,
    val sessions: ArrayList<SessionModel>?,
    val coachs: ArrayList<CoachModel>?
) : UserModel(uid, name, birthDate, sex, cpf, contactNumber, email, password) {
}