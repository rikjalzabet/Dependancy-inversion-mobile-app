package hr.foi.final_thesis.coderepeat.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.entities.UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.ILevel
import hr.foi.final_thesis.coderepeat.interfaces.ILevel_Task
import hr.foi.final_thesis.coderepeat.interfaces.ITask_UserAnswer
import hr.foi.final_thesis.coderepeat.interfaces.IUserAnswer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LevelSummaryAdapter(
    private val levelId: Int,
    private var iUserAnswers: IUserAnswer,
    private var iLevelTask: ILevel_Task,
    private var iTaskUserAnswer: ITask_UserAnswer,
    private var taskList: List<Task>,
    private var taskUserAnswerList: List<Pair<Task, List<UserAnswer>>>
    ): RecyclerView.Adapter<LevelSummaryAdapter.LevelSummaryHolder>(){
    private lateinit var taskQuestion: TextView
    private lateinit var userAnswer: TextView
    private lateinit var correctAnswer: TextView
    inner class LevelSummaryHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        init {
            taskQuestion = itemView.findViewById(R.id.item_level_summary_tv_task_question)
            userAnswer = itemView.findViewById(R.id.item_level_summary_tv_user_answer)
            correctAnswer = itemView.findViewById(R.id.item_level_summary_tv_correct_answer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelSummaryHolder {
        val view = View.inflate(parent.context, R.layout.item_level_summary, null)
        return LevelSummaryHolder(view)
    }

    override fun getItemCount(): Int = taskUserAnswerList.size

    override fun onBindViewHolder(holder: LevelSummaryHolder, position: Int) {
        val (task, userAnswers) = taskUserAnswerList[position]

        taskQuestion.text = task.question
        userAnswer.text = userAnswers[0].userAnswer
        correctAnswer.text = task.correctAnswer
    }
}