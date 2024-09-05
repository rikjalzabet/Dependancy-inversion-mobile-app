package hr.foi.final_thesis.coderepeat.adapters.tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.fragments.tasks.MatchTheAnswersTaskFragment
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler

class MatchTheAnswersAdapter(
    private val taskHandler: ITaskHandler
) : RecyclerView.Adapter<MatchTheAnswersAdapter.ViewHolder>(){
    private var questions: List<String> = emptyList()
    private var options: List<String> = emptyList()
    private val userAnswers = mutableListOf<String>()

    fun setTaskData(questions: List<String>, options: List<String>) {
        this.questions = questions
        this.options = options
        this.userAnswers.clear()
        repeat(questions.size){userAnswers.add("")}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return ViewHolder(view)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val leftItemTextView: TextView = itemView.findViewById(R.id.item_match_TV_LeftItem)
        val rightItemSpinner: Spinner = itemView.findViewById(R.id.item_match_TV_SPINNER_RightItems)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPosition = holder.adapterPosition
        if(currentPosition != RecyclerView.NO_POSITION) {
            holder.leftItemTextView.text = questions[currentPosition]
            Log.i("MatchTheAnswersAdapter", "Binding view holder for position $currentPosition: ${questions[currentPosition]}")
            val adapter = ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, options)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.rightItemSpinner.adapter = adapter

            holder.rightItemSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                    userAnswers[currentPosition] = "${questions[currentPosition]} ${options[pos]}"
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    userAnswers[currentPosition] = ""
                }
            }
        }
    }

    override fun getItemCount(): Int = questions.size
    fun getUserAnswers(): List<String> {
        return userAnswers
    }
    fun createFragment(taskId: Int, currentTaskIndex: Int, totalTasks: Int, levelId: Int): Fragment {
        return MatchTheAnswersTaskFragment(taskHandler, taskId, currentTaskIndex, totalTasks, levelId).apply {
            arguments = Bundle().apply {
                putInt("TASK_ID", taskId)
            }
        }
    }
}