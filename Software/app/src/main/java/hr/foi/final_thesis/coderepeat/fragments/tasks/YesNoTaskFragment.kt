package hr.foi.final_thesis.coderepeat.fragments.tasks

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
import hr.foi.final_thesis.coderepeat.entities.Task
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class YesNoTaskFragment(
    private val taskHandler: ITaskHandler,
    private var taskId: Int
) : Fragment() {

    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_yes_no_task, container, false)
    }*/

        private lateinit var questionTextView: TextView
        private lateinit var yesButton: RadioButton
        private lateinit var noButton: RadioButton
        private lateinit var nextButton: Button

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_yes_no_task, container, false)

            questionTextView = view.findViewById(R.id.fragment_yes_no_task_TV_Question)
            yesButton = view.findViewById(R.id.fragment_yes_no_task_RB_True)
            noButton = view.findViewById(R.id.fragment_yes_no_task_RB_False)
            nextButton = view.findViewById(R.id.fragment_yes_no_task_BTN_Next)

            taskId = arguments?.getInt("TASK_ID") ?: -1

            if (taskId != -1) {
                CoroutineScope(Dispatchers.IO).launch { displayTask() }
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

                val taskQuestion=  taskHandler.getTaskQuestion(taskId)
                questionTextView.text =taskQuestion
            }
        }

        private fun handleNextButtonClick() {
            val selectedAnswer = when {
                yesButton.isChecked -> "True"
                noButton.isChecked -> "False"
                else -> {
                    Toast.makeText(context, "Please select an answer", Toast.LENGTH_SHORT).show()
                    return
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                taskHandler.setUserAnswer(selectedAnswer, taskId, 1) // Save the answer

                // Log the results for verification
                val userAnswer = taskHandler.getUserLastAnswer()
                val isCorrect = taskHandler.validateUserAnswerWithCorrectAnswer(userAnswer.id, taskId)
                Log.d("YesNoTaskFragment", "User Answer: ${userAnswer.userAnswer}, ${taskHandler.getTask(taskId)?.question}")
                Log.d("YesNoTaskFragment", "Is Correct: $isCorrect, ${taskHandler.getTask(taskId)?.question}")

                withContext(Dispatchers.IO) {
                    (activity as LevelActivity).loadNextTask()
                }
            }
        }
}