package com.joma.notes

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoteFragment : Fragment(), NoteAdapter.IOnItem {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler)
        adapter = NoteAdapter(this)
        recyclerView.adapter = adapter

        val list: MutableList<NoteModel> = ArrayList()
        list.add(NoteModel("title", "sdfg", "sfg", ""))
        list.add(NoteModel("title2", "sdfg", "sfg", ""))
        list.add(NoteModel("title3", "sdfg", "sfg", ""))
        list.add(NoteModel("title4", "sdfg", "sfg", ""))
        adapter.setList(list)
    }

    override fun delete(pos: Int) {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(R.string.delete)
        alert.setMessage("Are you sure to delete")
        alert.setNegativeButton("Cancel", null)
        alert.setPositiveButton("Delete"){_, _->
            adapter.remove(pos)
        }
        alert.show()
    }

    override fun edit(pos: Int) {
        val bundle = Bundle()
        bundle.putSerializable("edit", adapter.getItem(pos))
        val fragment = AddFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }

    override fun share(pos: Int) {

    }
}