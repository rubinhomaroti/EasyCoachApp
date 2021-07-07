package br.com.fiap.easycoachapp.data.user.model

import java.util.Date

abstract class UserModel (
    val id: Int,
    val name: String,
    val birthDate: Date,
    val sex: Char,
    val cpf: String,
    val contactNumber: String?,
    val email: String,
    val password: String,
)