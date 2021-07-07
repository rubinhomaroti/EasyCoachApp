package br.com.fiap.easycoachapp.data.coach.model

import br.com.fiap.easycoachapp.data.coachee.model.CoacheeModel
import br.com.fiap.easycoachapp.data.session.model.SessionModel
import br.com.fiap.easycoachapp.data.sessionPackage.model.SessionPackageModel
import br.com.fiap.easycoachapp.data.specialty.model.SpecialtyModel
import br.com.fiap.easycoachapp.data.user.model.UserModel
import java.util.Date

class CoachModel(
    id: Int,
    name: String,
    birthDate: Date,
    sex: Char,
    cpf: String,
    contactNumber: String?,
    email: String,
    password: String,
    val cnpj: String?,
    val cancellationFee: Float,
    val sessions: ArrayList<SessionModel>?,
    val specialties: ArrayList<SpecialtyModel>?,
    val coachees: ArrayList<CoacheeModel>?,
    val sessionPackages: ArrayList<SessionPackageModel>?
) : UserModel(id, name, birthDate, sex, cpf, contactNumber, email, password)