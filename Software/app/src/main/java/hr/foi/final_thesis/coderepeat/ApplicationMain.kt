package hr.foi.final_thesis.coderepeat

import android.app.Application
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation
import hr.foi.final_thesis.coderepeat.service.ServiceLocator

class ApplicationMain : Application() {
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        //serviceLocator = ServiceLocator(applicationContext)
    }
    fun getServiceLocator(appNavigation: AppNavigation): ServiceLocator {
        serviceLocator = ServiceLocator(this, appNavigation)
        return serviceLocator
    }
}