package hr.foi.final_thesis.coderepeat.fragments.tasks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.activities.LevelActivity
import hr.foi.final_thesis.coderepeat.activities.LevelSummaryActivity
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FillTheBlankTaskFragment(
    private val taskHandler: ITaskHandler,
    private var taskId: Int,
    private val currentTaskIndex: Int,
    private val totalTasks: Int,
    private val levelId: Int
): Fragment() {
    private lateinit var questionTextView: TextView
    private lateinit var userAnswerEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fill_the_blank_task, container, false)

        questionTextView = view.findViewById(R.id.fragment_fill_the_blank_task_TV_Question)
        userAnswerEditText = view.findViewById(R.id.fragment_fill_the_blank_task_ET_UserAnswer)
        nextButton = view.findViewById(R.id.fragment_fill_the_blank_task_BTN_Next)

        taskId = arguments?.getInt("TASK_ID") ?: -1

        if (taskId != -1) {
            CoroutineScope(Dispatchers.IO).launch { displayTask() }
        }
        if(currentTaskIndex==totalTasks-1){
            nextButton.text="Finish"
        }else{
            nextButton.text="Next"
        }

        nextButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                handleNextButtonClick()
            }
        }
        return view
    }

    private suspend fun displayTask() {
        CoroutineScope(Dispatchers.IO).launch  {
            val taskQuestion = taskHandler.getTaskQuestion(taskId)
            questionTextView.text =taskQuestion
        }
    }

    private fun handleNextButtonClick() {
        var userAnswer = userAnswerEditText.text.toString().trim().lowercase()
        if (userAnswer.isEmpty() || userAnswer=="") {
            activity?.runOnUiThread {
                Toast.makeText(context, "Please fill in the blank", Toast.LENGTH_SHORT).show()
                userAnswer=""
            }
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            taskHandler.setUserAnswer(userAnswer, taskId, -1)

            val lastUserAnswer = taskHandler.getUserLastAnswer()
            val isCorrect =
                taskHandler.validateUserAnswerWithCorrectAnswer(lastUserAnswer.id, taskId)
            Log.d("TaskGameInfo", "FILL_IN_THE_BLANK - User Answer: $userAnswer")
            Log.d("TaskGameInfo", "FILL_IN_THE_BLANK - Is Correct: $isCorrect")
            if(isCorrect){
                taskHandler.updateTaskPoints(taskId, levelId)
            }
            withContext(Dispatchers.IO) {
                if(currentTaskIndex==totalTasks-1){
                    (activity as LevelActivity).loadNextTask()
                    LevelSummaryActivity.taskHandler = taskHandler
                    LevelSummaryActivity.levelId=levelId
                    val intent = Intent(context, LevelSummaryActivity::class.java)
                    startActivity(intent)
                }else {
                    (activity as LevelActivity).loadNextTask()
                }
            }
        }
    }
}