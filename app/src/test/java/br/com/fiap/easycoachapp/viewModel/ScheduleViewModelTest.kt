package br.com.fiap.easycoachapp.viewModel

import br.com.fiap.easycoachapp.data.coach.useCases.GetCurrentCoach
import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.helpers.IdGenerator
import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleContract
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel
import com.nhaarman.mockitokotlin2.times
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class ScheduleViewModelTest {

    var sut: ScheduleViewModel? = null

    private val contract: ScheduleContract = Mockito.mock(ScheduleContract::class.java)
    private val getCurrentCoach = Mockito.mock(GetCurrentCoachContract::class.java)
    private val deleteSession = Mockito.mock(DeleteSessionContract::class.java)
    private lateinit var session: SessionEntity

    @Before
    fun setup() {
        sut = ScheduleViewModel(
            contract,
            getCurrentCoach,
            deleteSession
        )

        session = SessionEntity(
            uid = IdGenerator().generate(),
            description = "Teste",
            sessionNumber = 1L,
            inviteUrl = "teste.com.br",
            hasCancellationFee = false,
            specialtyUid = IdGenerator().generate(),
            scheduledDateTime = Date(),
            title = "Teste"
        )
    }

    @Test
    fun `Should call go to new session activity when add button pressed`() {
        sut?.onSchedulePressed()
        Mockito.verify(contract, times(1)).scheduleNewSession()
    }

    @Test
    fun `Should call go to edit session activity when edit button pressed`() {
        sut?.onEditScheduledSessionPressed(session)
        Mockito.verify(contract, times(1)).goToScheduledSessionDetails(session)
    }

    @Test
    fun `Should call delete session activity when delete button pressed`() {
        sut?.onDeleteScheduledSessionPressed(session)
        Mockito.verify(deleteSession, times(1)).execute(session)
    }
}