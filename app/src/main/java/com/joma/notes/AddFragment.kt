package com.joma.notes

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.joma.notes.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    var imageUri: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        binding.date.setText(sdf.format(Date()))

        if (arguments != null) {
            binding.save.text = "edit"
            val mode = arguments?.getSerializable("edit") as NoteModel
            binding.title.setText(mode.title)

        }
        binding.save.setOnClickListener {
            val noteModel = NoteModel(
                binding.title.text.toString(),
                binding.desc.text.toString(),
                binding.date.text.toString(),
                imageUri
            )
            (requireActivity() as MainActivity).list.add(noteModel)
            findNavController().navigateUp()
        }

        binding.image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Glide.with(requireContext()).load(data?.data).into(binding.image)
                binding.image.setPadding(0, 0, 0, 0)
                imageUri = "${data?.data}"
            }
        }
    }

}