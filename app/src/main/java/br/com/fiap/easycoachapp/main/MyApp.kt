package br.com.fiap.easycoachapp.main

import android.app.Application
import br.com.fiap.easycoachapp.main.di.DataModules
import br.com.fiap.easycoachapp.main.di.ViewModelModules
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                DataModules.modules + ViewModelModules.modules
            )
        }
    }
}