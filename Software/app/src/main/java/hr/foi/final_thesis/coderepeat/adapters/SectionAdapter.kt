package hr.foi.final_thesis.coderepeat.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.AppDatabase
import hr.foi.final_thesis.coderepeat.database.LevelDAO
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.database.Section_LevelDAO
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ISection_Level
import hr.foi.final_thesis.coderepeat.interfaces.implementation.Section_Level_Intf_Impl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SectionAdapter(
    private val sections: List<Section>,
    private val sectionLevel: ISection_Level,
    private val levelTask: ILevel_Task,
    private val onLevelClick: (Level) -> Unit
) : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {
    inner class SectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val sectionTitle: TextView = itemView.findViewById(R.id.section_list_item_tv_section_name)
       val levelRecycleView: RecyclerView=itemView.findViewById(R.id.section_list_item_rv_listOfLevels)
        init{
            levelRecycleView.layoutManager= LinearLayoutManager(itemView.context)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.section_list_item, parent, false)
        return SectionViewHolder(view)
    }
    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.sectionTitle.text = section.title


        CoroutineScope(Dispatchers.IO).launch {
            val levels = sectionLevel.getLevelsForSection(section.id)
            Log.i("SectionAdapter", "SectionLevel: $levels")
            Log.i("SectionAdapter", "All the section list:: $sections")
            Log.i("SectionAdapter", "Item counter: ${getItemCount()}")

            withContext(Dispatchers.Main) {
                val levelAdapter=LevelAdapter(levels,levelTask,onLevelClick)
                holder.levelRecycleView.adapter=levelAdapter
            }
        }
    }
    override fun getItemCount(): Int = sections.size
}