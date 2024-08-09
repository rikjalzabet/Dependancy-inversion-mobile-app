package hr.foi.final_thesis.coderepeat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.adapters.SectionAdapter
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.SectionDAO
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var sectionDao: SectionDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_section, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.fragment_list_section_rv_listOfSections)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val db = AppDatabase.getDatabase(requireContext())
        sectionDao = db.sectionDao()

        CoroutineScope(Dispatchers.IO).launch {
            val sections = sectionDao.getAllSections()
            Log.i("ListSectionFragment", "Sections: ${db.section_levelDao()}")

            val sectionAdapter = SectionAdapter(sections, db.section_levelDao())
            recyclerView.adapter = sectionAdapter
        }
    }

}