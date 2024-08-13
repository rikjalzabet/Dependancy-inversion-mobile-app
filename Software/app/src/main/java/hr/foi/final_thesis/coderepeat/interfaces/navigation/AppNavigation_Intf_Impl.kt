package hr.foi.final_thesis.coderepeat.interfaces.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AppNavigation_Intf_Impl(
    private val activity: AppCompatActivity,
    private val fragmentList: List<Fragment>
):AppNavigation {
    override fun navigateToHome() {
        val fragment=fragmentList[0]
        activityCall(fragment)
    }

    private fun activityCall(fragment: Fragment){
        activity
            .supportFragmentManager
            .beginTransaction()
            .replace(hr.foi.final_thesis.coderepeat.R.id.activity_main_FL_main_container, fragment)
            .commit()
    }
}