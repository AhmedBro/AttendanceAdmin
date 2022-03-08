package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Fcih.attendance_admin.R
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.CourseList.CourseListViewModel
import com.Fcih.attendance_admin.Domain.Constants
import com.Fcih.attendance_admin.Domain.InitFireStore

class CourseListFragment : Fragment() {
    lateinit var courseListViewModel: CourseListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_course_list, container, false)

        val application = requireNotNull(this.activity).application

        val rv = view.findViewById<RecyclerView>(R.id.mCourseList)


        courseListViewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        val manager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        rv.layoutManager = manager

//        var coursesList = mutableListOf(
//            Course("CS-401", "Pl2", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-402", "Information Retrieval", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-403", "ICM", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-404", "Data Warehouse", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-405", "Operating System", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-406", "Data Communication", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-407", "Electronics", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-408", "Pl2", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-409", "Pl2", "sunday", "10:00 to 11:59 am", "h1"),
//            Course("CS-410", "Pl2", "sunday", "10:00 to 11:59 am", "h1"),
//        )
        var adapter = CourseAdapter()
//        adapter.submitList(coursesList)
        adapter.setOnItemClickListener {
//            Navigation.findNavController(this.requireView())
//                .navigate()
            Toast.makeText(application, it.courseCode, Toast.LENGTH_SHORT).show()
        }

        rv.adapter = adapter


        var addCourseTv = view?.findViewById<TextView>(R.id.mAddCourseTv)
        addCourseTv?.setOnClickListener {
            Navigation.findNavController(this.requireView())
                .navigate(CourseListFragmentDirections.actionCourseListFragmentToAddCourseFragment())

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        InitFireStore.instance.collection(Constants.STUDENTS_TABLE).document("2018").collection(Constants.STUDENT_DATA)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.e("Adminnnnnnnnnnnnnnnnn", "${document.id} => ${document.data.get("studentID")}")
                }


            }
            .addOnFailureListener { exception ->
                Log.e("Adminnnnnnnnnnnnnnnnn", "Error getting documents: ", exception)
            }
    }
}