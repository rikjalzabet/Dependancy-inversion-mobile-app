package hr.foi.final_thesis.coderepeat.fragments.tasks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
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

class MultipleChoiceTaskFragment(
    private val taskHandler: ITaskHandler,
    private var taskId: Int,
    private val currentTaskIndex: Int,
    private val totalTasks: Int,
    private val levelId: Int
) : Fragment() {


   /* override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_multiple_choice_task, container, false)
    }*/
    private lateinit var questionTextView: TextView
    private lateinit var checkboxesLayout: LinearLayout
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_multiple_choice_task, container, false)

        questionTextView = view.findViewById(R.id.fragment_multiple_choice_task_TV_Question)
        checkboxesLayout = view.findViewById(R.id.fragment_multiple_choice_task_LL_Checkboxes)
        nextButton = view.findViewById(R.id.fragment_multiple_choice_task_BTN_Next)

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
        val taskQuestion = taskHandler.getTaskQuestion(taskId)
        withContext(Dispatchers.Main) {
            questionTextView.text = taskQuestion
        }

        val taskOptions = taskHandler.getTaskOptions(taskId)
        withContext(Dispatchers.Main) {
            checkboxesLayout.removeAllViews()
        }
        taskOptions.forEach { option ->
            withContext(Dispatchers.Main) {
                val checkBox = CheckBox(context)
                checkBox.text = option
                checkboxesLayout.addView(checkBox)
            }
        }
    }

    private suspend fun handleNextButtonClick() {
        val selectedAnswers = mutableListOf<String>()
        for (i in 0 until checkboxesLayout.childCount) {
            val checkBox = checkboxesLayout.getChildAt(i) as CheckBox
            if (checkBox.isChecked) {
                selectedAnswers.add(checkBox.text.toString())
            }
        }
        if (selectedAnswers.isEmpty()) {
            activity?.runOnUiThread {
                Toast.makeText(context, "Please select at least one answer", Toast.LENGTH_SHORT)
                    .show()
            }
            return
        }

        val selectedAnswersString = selectedAnswers.joinToString("|##|")

        CoroutineScope(Dispatchers.IO).launch {
            taskHandler.setUserAnswer(selectedAnswersString, taskId, -1)

            val userAnswer = taskHandler.getUserLastAnswer()
            val isCorrect = taskHandler.validateUserAnswerWithCorrectAnswer(userAnswer.id, taskId)
            Log.d("TaskGameInfo", "MULTIPLE_CHOICE_MULTIPLE_ANSWERS - User Answer: $selectedAnswersString, ${taskHandler.getTask(taskId)?.question}")
            Log.d("TaskGameInfo", "MULTIPLE_CHOICE_MULTIPLE_ANSWERS - Is Correct: $isCorrect, ${taskHandler.getTask(taskId)?.question}")
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