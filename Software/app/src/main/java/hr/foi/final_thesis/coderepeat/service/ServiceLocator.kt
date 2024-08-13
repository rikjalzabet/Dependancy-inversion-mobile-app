package hr.foi.final_thesis.coderepeat.service

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import hr.foi.final_thesis.coderepeat.fragments.ListLevelFragment
import hr.foi.final_thesis.coderepeat.fragments.ListSectionFragment
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation
import hr.foi.final_thesis.coderepeat.interfaces.navigation.AppNavigation_Intf_Impl

class ServiceLocator(private val context: Context) {
    fun provideNavigator(activity: AppCompatActivity): AppNavigation {
        return AppNavigation_Intf_Impl(activity, provideFragmentList())
    }
    private fun provideFragmentList(): List<Fragment> {
        return listOf(ListSectionFragment(), ListLevelFragment())
    }
}