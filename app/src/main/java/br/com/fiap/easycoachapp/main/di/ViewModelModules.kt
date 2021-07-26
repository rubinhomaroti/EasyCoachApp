package br.com.fiap.easycoachapp.main.di

import br.com.fiap.easycoachapp.domain.usecases.coach.GetCurrentCoachContract
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import br.com.fiap.easycoachapp.domain.usecases.session.GetSessionsContract
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleContract
import br.com.fiap.easycoachapp.viewModel.schedule.ScheduleViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

object ViewModelModules {
    val modules = module {
        viewModel { (contract: ScheduleContract) ->
            ScheduleViewModel(
                contract,
                get<GetCurrentCoachContract>(),
                get<GetSessionsContract>(),
                get<DeleteSessionContract>()
            )}
    }
}