package hr.foi.final_thesis.coderepeat.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.activities.LevelActivity
import hr.foi.final_thesis.coderepeat.adapters.LevelAdapter
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.database.TaskDAO
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListLevelFragment : Fragment() {
    private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var taskDAO: TaskDAO
    private lateinit var recyclerView: RecyclerView
    private var levelId: Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        levelTaskDao=AppDatabase.getDatabase(requireContext()).level_taskDao()
        taskDAO=AppDatabase.getDatabase(requireContext()).taskDao()
        recyclerView = view.findViewById(R.id.fragment_list_level_rv_ListOfLevels)
        recyclerView.layoutManager=LinearLayoutManager(context)

        CoroutineScope(Dispatchers.IO).launch {
            val tasks = levelTaskDao.getTasksForLevel(levelId)
            withContext(Dispatchers.Main) {
                //recyclerView.adapter= TaskAdapter(tasks)
            }
        }
    }
}