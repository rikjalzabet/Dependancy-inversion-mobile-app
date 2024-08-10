package hr.foi.final_thesis.coderepeat.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.database.Level_TaskDAO
import hr.foi.final_thesis.coderepeat.entities.Level


class LevelAdapter(
    private val levels: List<Level>,
    private val levelTaskDao: Level_TaskDAO,
    private val onLevelClick: (Level) -> Unit
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val levelNumber = itemView.findViewById<TextView>(R.id.level_list_item_tv_level_number)
        internal val levelName = itemView.findViewById<TextView>(R.id.level_list_item_tv_level_name)

        init {
            itemView.setOnClickListener {
                val position=adapterPosition
                if(position!= RecyclerView.NO_POSITION){
                    val level=levels[position]
                    onLevelClick(level)
                }
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
        }

        override fun getItemCount(): Int = levels.size
}

