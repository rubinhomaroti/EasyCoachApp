package br.com.fiap.easycoachapp.viewModel.schedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import org.joda.time.DateTimeComparator
import java.util.*
import kotlin.collections.ArrayList

class ScheduleViewModel(
    private val contract: ScheduleContract,
    private val getCurrentCoach: GetCurrentCoachContract,
    private val deleteSession: DeleteSessionContract
) : ViewModel() {
    val sessions = MutableLiveData<ArrayList<SessionEntity>>()
    private val sessionsList = ArrayList<SessionEntity>()

    fun onCreate() {
        onScheduleDateChanged(Date())
    }

    fun onScheduleDateChanged(selectedDate: Date) {
        val dateTimeComparator = DateTimeComparator.getDateOnlyInstance()

        getCurrentCoach.execute({ coach ->
            if (coach.coachees != null && coach.coachees.isNotEmpty()) {
                sessionsList.clear()

                coach.coachees.forEach { coachee ->
                    val s = coachee.sessions?.filter {
                        dateTimeComparator.compare(it.scheduledDateTime, selectedDate) == 0
                    }

                    if (s != null && s.isNotEmpty()) {
                        s.forEach {
                            coachee.coach = coach
                            it.coachee = coachee
                            sessionsList.add(it)
                        }
                    }
                }
                sessions.postValue(sessionsList)
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
        deleteSession.execute(selectedSession)

        if (sessionsList.contains(selectedSession)) {
            sessionsList.remove(selectedSession)
            sessions.postValue(sessionsList)
        }
    }
}