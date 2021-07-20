package br.com.fiap.easycoachapp.domain.helpers

import java.util.UUID

class IdGenerator {
    fun generate(): String {
        return UUID.randomUUID().toString()
    }
}