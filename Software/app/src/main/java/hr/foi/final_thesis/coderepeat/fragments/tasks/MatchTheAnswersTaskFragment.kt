package hr.foi.final_thesis.coderepeat.fragments.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.activities.LevelActivity
import hr.foi.final_thesis.coderepeat.adapters.tasks.MatchTheAnswersAdapter
import hr.foi.final_thesis.coderepeat.interfaces.tasks.ITaskHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MatchTheAnswersTaskFragment (
    private val taskHandler: ITaskHandler,
    private var taskId: Int
): Fragment() {
    private lateinit var questionTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_match_the_answers_task, container, false)

        questionTextView = view.findViewById(R.id.fragment_match_the_answers_task_TV_Question)
        recyclerView = view.findViewById(R.id.fragment_match_the_answers_task_RV_Matching)
        nextButton = view.findViewById(R.id.fragment_match_the_answers_task_BTN_Next)

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
        CoroutineScope(Dispatchers.IO).launch {
            val questions = taskHandler.getTaskQuestionMultiple(taskId)
            val options = taskHandler.getTaskOptions(taskId)

            withContext(Dispatchers.Main) {
            Log.i("Questions and options: ", "$questions, $options")
                questionTextView.text="Match the following items:"
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = MatchTheAnswersAdapter(questions,options)
            }
        }
    }

    private fun handleNextButtonClick() {
        val adapter=recyclerView.adapter as MatchTheAnswersAdapter
        val userAnswer = adapter.getUserAnswers()

        if(userAnswer.isEmpty()){
            activity?.runOnUiThread {
                Toast.makeText(context, "Please match all items", Toast.LENGTH_SHORT).show()
            }
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            taskHandler.setUserAnswer(userAnswer.joinToString("|##|"), taskId, -1)

            val lastUserAnswer = taskHandler.getUserLastAnswer()
            val isCorrect =
                taskHandler.validateUserAnswerWithCorrectAnswer(lastUserAnswer.id, taskId)

            Log.d("TaskGameInfo", "MATCH_THE_ANSWERS - User Answer: ${lastUserAnswer.userAnswer}, ${taskHandler.getTask(taskId)?.question}")
            Log.d("TaskGameInfo", "MATCH_THE_ANSWERS - Is Correct: $isCorrect, ${taskHandler.getTask(taskId)?.question}")

            withContext(Dispatchers.IO) {
                (activity as LevelActivity).loadNextTask()
            }
        }
    }
}