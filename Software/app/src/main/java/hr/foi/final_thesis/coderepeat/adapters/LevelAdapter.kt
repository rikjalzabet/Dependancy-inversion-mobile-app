package hr.foi.final_thesis.coderepeat.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task


class LevelAdapter(
    private val levels: List<Level>,
    private val levelTask: ILevel_Task,
    private val onLevelClick: (Level) -> Unit
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val levelNumber = itemView.findViewById<TextView>(R.id.level_list_item_tv_level_number)
        internal val levelName = itemView.findViewById<TextView>(R.id.level_list_item_tv_level_name)
        internal val levelLayout = itemView.findViewById<LinearLayout>(R.id.level_list_item_ll_level_info)

        init {
            itemView.setOnClickListener {
                val level = levels[adapterPosition]
                Log.i("LevelAdapter", "Level clicked: ${level.name}")
                onLevelClick(level)
                Log.i("LevelAdapter", "you made a click on item!")
            }

            levelLayout.setOnClickListener {
                val level = levels[adapterPosition]
                Log.i("LevelAdapter", "Layout Level clicked: ${level.name}")
                onLevelClick(level)
                Log.i("LevelAdapter", "Layout you made a click on item!")
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.level_list_item, parent, false)
            return LevelViewHolder(view)
        }

        override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
            val level=levels[position]
            val lvlNmbCounter=position+1
            holder.levelName.text=level.name
            holder.levelNumber.text="Level $lvlNmbCounter"

            holder.itemView.setOnClickListener {
                Log.i("LevelAdapter","Clicked Item directly ${level.name}")
            }
        }
        override fun getItemCount(): Int = levels.size
}

