package br.com.fiap.easycoachapp.viewModel.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import java.util.Date
import kotlin.collections.ArrayList

class ScheduleViewModel(
    private val contract: ScheduleContract,
    private val getCurrentCoach: GetCurrentCoachContract,
    private val deleteSession: DeleteSessionContract
) : ViewModel() {
    val coachees = MutableLiveData<ArrayList<CoacheeEntity>>()

    fun onCreate() {
        onScheduleDateChanged(Date())
    }

    fun onScheduleDateChanged(selectedDate: Date) {
        this.coachees.value?.clear()
        getCurrentCoach.execute({ coach ->
            val coacheesFound = coach.coachees?.filter { c -> c.sessions != null &&
                                                              c.sessions.any { s -> s.scheduledDateTime == selectedDate }}
            if (coacheesFound != null && coacheesFound.isNotEmpty()) {
                coachees.postValue(ArrayList(coacheesFound))
            }
        },
        { contract.showErrorMessage() })
    }

    fun onSchedulePressed() {
        contract.scheduleNewSession()
    }

    fun onEditScheduledSessionPressed(selectedSession: SessionEntity) {
        contract.goToScheduledSessionDetails(selectedSession)
    }

    fun onDeleteScheduledSessionPressed(selectedSession: SessionEntity) {
        if (contract.requestConfirmation("Deseja realmente cancelar a sessão?")) {
            deleteSession.execute(selectedSession)
        }
    }
}