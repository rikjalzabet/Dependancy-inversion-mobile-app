package hr.foi.final_thesis.coderepeat.adapters.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import hr.foi.final_thesis.coderepeat.fragments.tasks.MultipleChoiceTaskFragment
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler

class MultipleChoiceAdapter (
    private val taskHandler: ITaskHandler
) {
    fun createFragment(taskId: Int): Fragment {
        return MultipleChoiceTaskFragment(taskHandler, taskId).apply {
            arguments = Bundle().apply {
                putInt("TASK_ID", taskId)
            }
        }
    }
}