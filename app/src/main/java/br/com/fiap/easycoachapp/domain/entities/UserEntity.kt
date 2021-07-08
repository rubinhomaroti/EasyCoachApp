package br.com.fiap.easycoachapp.domain.entities

import java.util.Date

abstract class UserEntity (
    val uid: String,
    val name: String,
    val birthDate: Date,
    val sex: Char,
    val cpf: String,
    val contactNumber: String?,
    val email: String,
    val password: String,
)