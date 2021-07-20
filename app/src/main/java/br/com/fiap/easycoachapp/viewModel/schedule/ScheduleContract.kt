package br.com.fiap.easycoachapp.viewModel.schedule

import br.com.fiap.easycoachapp.domain.entities.SessionEntity

interface ScheduleContract {
    fun scheduleNewSession()
    fun goToScheduledSessionDetails(session: SessionEntity)
    fun requestConfirmation() : Boolean
    fun showErrorMessage()
}