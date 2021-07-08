package br.com.fiap.easycoachapp.domain.entities

import java.util.Date
import kotlin.collections.ArrayList

class CoacheeEntity (
    uid: String,
    name: String,
    birthDate: Date,
    sex: Char,
    cpf: String,
    contactNumber: String?,
    email: String,
    password: String,
    val sessions: ArrayList<SessionEntity>?,
    val coachs: ArrayList<CoachEntity>?
) : UserEntity(uid, name, birthDate, sex, cpf, contactNumber, email, password)