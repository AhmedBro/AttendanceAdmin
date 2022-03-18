package com.Fcih.attendance_admin.Desgin.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.Fcih.attendance_admin.Data.Students.SearchStudentViewModel
import com.Fcih.attendance_admin.Desgin.Activities.MainActivity
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_search_student.*
import kotlinx.android.synthetic.main.fragment_search_student.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchStudentFragment : Fragment() {

    lateinit var searchStudentViewModel: SearchStudentViewModel
    lateinit var studentId: String
    lateinit var year: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchStudentViewModel = ViewModelProvider(this).get(SearchStudentViewModel::class.java)

        mSearchBtn.setOnClickListener {
            mSearchProgressBar.visibility = View.VISIBLE
            mStudent.visibility = View.INVISIBLE
            if (Validate()) {
                studentId = mStudentIdEt.text.toString()
                year = studentId.subSequence(0, 4).toString()
                lifecycleScope.launch(Dispatchers.IO) {
                    searchStudentViewModel.getStudent(year, studentId)
                }
            } else {
                mSearchProgressBar.visibility = View.INVISIBLE
            }
        }


        mStudent.setOnLongClickListener {

            delete(it.mStudentId.text.toString())

            return@setOnLongClickListener true
        }



        searchStudentViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (!it) {
                mSearchProgressBar.visibility = View.INVISIBLE
            }
        })
        searchStudentViewModel.success.observe(viewLifecycleOwner, Observer {
            if (it) {
                mStudent.mStudentId.text = studentId
                mStudent.visibility = View.VISIBLE
            } else {
                mStudent.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), "No matching student!!", Toast.LENGTH_SHORT).show()
            }
        })

        searchStudentViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun delete(studentId: String) {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(MainActivity.context)
        builder1.setMessage("Delete this Student?!")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Delete",
            DialogInterface.OnClickListener { dialog, id ->

                searchStudentViewModel.deleteCourse(
                    studentId.subSequence(0, 4).toString(),
                    studentId
                )

                mStudent.visibility = View.INVISIBLE

                dialog.cancel()

            })

        builder1.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert11: AlertDialog = builder1.create()
        alert11.show()

    }

    fun Validate(): Boolean {

        if (mStudentIdEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter an Id", Toast.LENGTH_LONG).show()
            return false
        } else if (mStudentIdEt.text.toString().length < 4) {
            Toast.makeText(activity, "Too short Id", Toast.LENGTH_LONG).show()
            return false
        } else return true

    }

}