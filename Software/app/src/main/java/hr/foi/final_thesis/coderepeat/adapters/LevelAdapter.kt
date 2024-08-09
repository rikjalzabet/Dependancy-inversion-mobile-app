package hr.foi.final_thesis.coderepeat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section


class LevelAdapter{}
    /*(private val levels: List<Level>) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val levelNumber = itemView.findViewById<TextView>(R.id.level_list_item_tv_level_number)
        private val levelName = itemView.findViewById<TextView>(R.id.level_list_item_tv_level_name)

        fun bind(level: Level) {
            levelNumber.text = level.id.toString()
            levelName.text = level.name
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.level_list_item, parent, false)
            return LevelViewHolder(view)
        }

        override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
            holder.bind(levels[position])


        }

        override fun getItemCount(): Int = levels.size
}
*/
