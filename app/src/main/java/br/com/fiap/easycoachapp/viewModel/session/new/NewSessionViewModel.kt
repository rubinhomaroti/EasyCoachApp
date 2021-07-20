package br.com.fiap.easycoachapp.viewModel.session.new

import androidx.lifecycle.ViewModel
import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.helpers.IdGenerator
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.CreateSessionContract
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
    lateinit var sessionPackage: SessionPackageEntity

    var id = IdGenerator().generate()
    var scheduledDateTimeStart: Date = Date()
    var scheduledDateTimeEnd: Date = Date()
    var sessionNumber = 1
    var title = ""
    var description = ""
    var inviteUrl = ""
    var hasCancellationFee: Boolean = false
    var coachees: ArrayList<CoacheeEntity>? = ArrayList<CoacheeEntity>()
    var specialties: ArrayList<SpecialtyEntity>? = ArrayList<SpecialtyEntity>()

    fun onCreate() {
        getCurrentCoach.execute({ coach ->
            currentCoach = coach
            coachees = coach.coachees
            specialties = coach.specialties
            sessionPackage = coach.sessionPackages!!.first()
        },
        {contract.showErrorMessage()})
    }

    fun onCreateSessionPressed() {
        createSession.execute(
            SessionEntity(
                id,
                scheduledDateTimeStart,
                scheduledDateTimeEnd,
                sessionNumber,
                title,
                description,
                inviteUrl,
                hasCancellationFee,
                currentCoach,
                selectedCoachee,
                selectedSpecialty,
                sessionPackage
        ),
            {contract.goToHomeActivity()},
            {contract.showErrorMessage()})
    }
}