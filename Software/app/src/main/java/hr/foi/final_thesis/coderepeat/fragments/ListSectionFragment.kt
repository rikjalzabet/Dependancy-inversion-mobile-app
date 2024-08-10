package hr.foi.final_thesis.coderepeat.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.activities.LevelActivity
import hr.foi.final_thesis.coderepeat.adapters.SectionAdapter
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.database.SectionDAO
import hr.foi.final_thesis.coderepeat.database.Section_LevelDAO
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.ITask
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Level_Task_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Level_Intf_Impl
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Task_Intf_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import populateData

class ListSectionFragment : Fragment() {
    private lateinit var sectionDao: SectionDAO
    private lateinit var sectionLevelDao: Section_LevelDAO
    private lateinit var levelTaskDao: Level_TaskDAO
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_section, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.fragment_list_section_rv_listOfSections)
        progressBar= view.findViewById(R.id.fragment_list_section_pb_loading)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val db = AppDatabase.getDatabase(requireContext())
        sectionDao = db.sectionDao()
        sectionLevelDao = db.section_levelDao()
        levelTaskDao = db.level_taskDao()


        CoroutineScope(Dispatchers.IO).launch {
            val sections = sectionDao.getAllSections()
            Log.i("ListSectionFragment", "Fetched Sections: $sections")
            withContext(Dispatchers.Main) {
                val adapter = SectionAdapter(sections, sectionLevelDao, levelTaskDao) { level ->
                    Log.i("ListSectionFragment", "Clicked level: ${level.id} and ${level.name}")
                    val intent = Intent(context, LevelActivity::class.java)
                    intent.putExtra("LEVEL_ID", level.id)
                    startActivity(intent)
                }
                recyclerView.adapter = adapter
            }
        }
    }
}