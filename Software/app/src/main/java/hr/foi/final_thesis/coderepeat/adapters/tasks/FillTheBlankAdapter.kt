package hr.foi.final_thesis.coderepeat.adapters.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import hr.foi.final_thesis.coderepeat.fragments.tasks.FillTheBlankTaskFragment
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler

class FillTheBlankAdapter (
    private val taskHandler: ITaskHandler
) {
    fun createFragment(taskId: Int, currentTaskIndex: Int, totalTasks: Int): Fragment {
        return FillTheBlankTaskFragment(taskHandler, taskId, currentTaskIndex, totalTasks).apply {
            arguments = Bundle().apply {
                putInt("TASK_ID", taskId)
            }
        }
    }
}