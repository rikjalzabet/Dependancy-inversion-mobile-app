package hr.foi.final_thesis.coderepeat

import android.app.Application
import hr.foi.final_thesis.coderepeat.service.ServiceLocator

class ApplicationMain : Application() {
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
    }
}