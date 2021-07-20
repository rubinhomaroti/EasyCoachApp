package br.com.fiap.easycoachapp.viewModel.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import br.com.fiap.easycoachapp.domain.usecases.session.EditSessionContract
import br.com.fiap.easycoachapp.domain.usecases.session.GetSessionsContract

class ScheduleViewModel(
    private val contract: ScheduleContract,
    private val getCurrentCoach: GetCurrentCoachContract,
    private val getSessions: GetSessionsContract,
    private val editSession: EditSessionContract,
    private val deleteSession: DeleteSessionContract
) : ViewModel() {
    val sessions = MutableLiveData<ArrayList<SessionEntity>>()

    fun onCreate() {
        getCurrentCoach.execute({ coach ->
            getSessions.execute(coach,{ sessions ->
                this.sessions.postValue(sessions)
            },
            {contract.showErrorMessage()})
        },
        { contract.showErrorMessage() })
    }

    fun onSchedulePressed() {
        contract.scheduleNewSession()
    }

    fun onEditScheduledSessionPressed(selectedSession: SessionEntity) {
        if (contract.requestConfirmation()) {
            editSession.execute(selectedSession)
        }
    }

    fun onDeleteScheduledSessionPressed(selectedSession: SessionEntity) {
        if (contract.requestConfirmation()) {
            deleteSession.execute(selectedSession)
        }
    }
}