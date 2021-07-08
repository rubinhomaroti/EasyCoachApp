package br.com.fiap.easycoachapp.domain.usecases.session

import br.com.fiap.easycoachapp.data.session.model.SessionModel

interface DeleteSessionContract {
    fun execute(session: SessionModel)
}