package hr.foi.final_thesis.coderepeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.final_thesis.coderepeat.R
import hr.foi.final_thesis.coderepeat.entities.Level
import hr.foi.final_thesis.coderepeat.entities.Section


class ListLevelFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sectionId: Section
    private lateinit var levels: List<Level>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_level, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //recyclerView=view.findViewById(R.id.fragment_list_level_rv_ListOfLevels)
        //recyclerView.adapter= LevelAdapter()
        //recyclerView.layoutManager= LinearLayoutManager(view.context)
    }


}