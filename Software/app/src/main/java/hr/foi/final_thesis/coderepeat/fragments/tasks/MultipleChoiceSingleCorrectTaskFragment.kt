package hr.foi.final_thesis.coderepeat.fragments.tasks

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
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


class MultipleChoiceSingleCorrectTaskFragment(
    private val taskHandler: ITaskHandler,
    private var taskId: Int,
    private val currentTaskIndex: Int,
    private val totalTasks: Int,
    private val levelId: Int
) : Fragment() {
    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_multiple_choice_single_correct_task, container, false)

        questionTextView = view.findViewById(R.id.fragment_multiple_choice_single_correct_task_TV_Question)
        optionsRadioGroup = view.findViewById(R.id.fragment_multiple_choice_single_correct_task_RG_Options)
        nextButton = view.findViewById(R.id.fragment_multiple_choice_single_correct_task_BTN_Next)

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
        taskOptions.forEach { option ->
            withContext(Dispatchers.Main) {
                val radioButton = RadioButton(context)
                radioButton.text = option
                optionsRadioGroup.addView(radioButton)
            }
        }
    }

    private fun handleNextButtonClick() {
        val selectedOptionId = optionsRadioGroup.checkedRadioButtonId
        if (selectedOptionId == -1) {
            activity?.runOnUiThread {
                Toast.makeText(context, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
            return
        }

        val selectedRadioButton = view?.findViewById<RadioButton>(selectedOptionId)
        val selectedAnswer = selectedRadioButton?.text.toString()

        CoroutineScope(Dispatchers.IO).launch{
            taskHandler.setUserAnswer(selectedAnswer, taskId, -1)

            val userAnswer = taskHandler.getUserLastAnswer()
            val isCorrect = taskHandler.validateUserAnswerWithCorrectAnswer(userAnswer.id, taskId)
            Log.d("TaskGameInfo", "MULTIPLE_CHOICE_SINGLE_ANSWER - User Answer: ${userAnswer.userAnswer}, ${taskHandler.getTask(taskId)?.question}")
            Log.d("TaskGameInfo", "MULTIPLE_CHOICE_SINGLE_ANSWER - Is Correct: $isCorrect, ${taskHandler.getTask(taskId)?.question}")

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