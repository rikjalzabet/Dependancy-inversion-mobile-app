package hr.foi.final_thesis.coderepeat.fragments.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.foi.final_thesis.coderepeat.R


class MultipleChoiceSingleCorrectTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_multiple_choice_single_correct_task,
            container,
            false
        )
    }

}