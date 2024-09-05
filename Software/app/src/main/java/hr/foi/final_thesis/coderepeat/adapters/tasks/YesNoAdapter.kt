package hr.foi.final_thesis.coderepeat.adapters.tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.fragments.tasks.YesNoTaskFragment
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler

class YesNoAdapter(
private val taskHandler: ITaskHandler
) {
    fun createFragment(taskId: Int, currentTaskIndex: Int, totalTasks: Int, levelId: Int): Fragment {
        return YesNoTaskFragment(taskHandler, taskId, currentTaskIndex, totalTasks, levelId).apply {
            arguments = Bundle().apply {
                putInt("TASK_ID", taskId)
            }
        }
    }
}