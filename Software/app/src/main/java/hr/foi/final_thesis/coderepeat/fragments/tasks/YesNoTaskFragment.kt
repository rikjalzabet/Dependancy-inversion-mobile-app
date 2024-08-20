package hr.foi.final_thesis.coderepeat.fragments.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Task


class YesNoTaskFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_yes_no_task, container, false)
    }
}