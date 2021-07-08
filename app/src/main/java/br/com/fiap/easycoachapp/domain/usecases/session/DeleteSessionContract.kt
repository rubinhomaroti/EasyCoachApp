package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.domain.entities.SessionEntity

interface DeleteSessionContract {
    fun execute(session: SessionEntity)
}