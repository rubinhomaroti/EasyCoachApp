package br.com.fiap.easycoachapp.viewModel.session.create

import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.helpers.IdGenerator
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.CreateSessionContract
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.collections.ArrayList

class NewSessionViewModel(
    private val contract: NewSessionContract,
    private val createSession: CreateSessionContract,
    private val getCurrentCoach: GetCurrentCoachContract
) : ViewModel() {
    lateinit var currentCoach: CoachEntity
    lateinit var selectedCoachee: CoacheeEntity
    lateinit var selectedSpecialty: SpecialtyEntity

    var id = IdGenerator().generate()
    var scheduledDateTime = Date()
    var sessionNumber = 1L
    var title = ""
    var description = ""
    var inviteUrl = ""
    var hasCancellationFee: Boolean = false
    var coachees: ArrayList<CoacheeEntity> = ArrayList()
    var specialties: ArrayList<SpecialtyEntity> = ArrayList()

    fun onCreate() {
        getCurrentCoach.execute({ coach ->
            currentCoach = coach
            if (coach.coachees != null) {
                coachees.addAll(coach.coachees)
            }
            if (coach.specialties != null) {
                specialties.addAll(coach.specialties)
            }
            contract.onDataLoaded()
        },
            { contract.showErrorMessage() })
    }

    fun onCreateSessionPressed() {
        createSession.execute(
            currentCoach,
            selectedCoachee,
            SessionEntity(
                id,
                scheduledDateTime,
                sessionNumber,
                title,
                description,
                inviteUrl,
                hasCancellationFee,
                selectedSpecialty.uid,
            ),
            { contract.goToHomeActivity() },
            { contract.showErrorMessage() })
    }
}