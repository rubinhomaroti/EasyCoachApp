package br.com.fiap.easycoachapp.domain.entities

import java.util.Date
import kotlin.collections.ArrayList

class CoachEntity (
    uid: String,
    name: String,
    birthDate: Date,
    sex: Char,
    cpf: String,
    contactNumber: String?,
    email: String,
    password: String,
    val cnpj: String?,
    val cancellationFee: Float,
    val sessions: ArrayList<SessionEntity>?,
    val specialties: ArrayList<SpecialtyEntity>?,
    val coachees: ArrayList<CoacheeEntity>?,
    val sessionPackages: ArrayList<SessionPackageEntity>?
) : UserEntity(uid, name, birthDate, sex, cpf, contactNumber, email, password)