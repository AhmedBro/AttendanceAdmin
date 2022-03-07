package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.CourseList.CourseListViewModel
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_add_course.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class AddCourseFragment : Fragment(R.layout.fragment_add_course) {
    lateinit var courseGroup: String
    lateinit var courseViewModel: CourseListViewModel
    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null
    private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.US)

    var calendar: Calendar? = null
    private val dateFormatter = SimpleDateFormat("E", Locale.US)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseViewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)
        calendar = Calendar.getInstance()



        mGroupsp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                courseGroup = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


        mAddCourseBtn.setOnClickListener {
            var courseName = mCourseNameEt.text.toString()
            var courseCode = mCourseCodeEt.text.toString()
            var courseDate = mCourseDateEt.text.toString()
            var courseStartTime = mStartTimeEt.text.toString()
            var courseEndTime = mEndTimeEt.text.toString()
            var coursePlace = mCoursePlaceEt.text.toString()
            mAddCourseProgressBar.visibility = View.VISIBLE
            lifecycleScope.launch(Dispatchers.IO) {
                courseViewModel.addCourse(
                    Course(
                        courseCode,
                        courseName,
                        courseDate,
                        courseStartTime,
                        courseEndTime,
                        coursePlace,
                        courseGroup
                    )
                )
            }


        }


        Observers()


        mStartTimeEt.setOnClickListener {
            setTimeField(mStartTimeEt)
        }
        mEndTimeEt.setOnClickListener {
            setTimeField(mEndTimeEt)
        }
        mCourseDateEt.setOnClickListener {
            setDateTimeField()
        }

    }

    private fun Observers() {
        courseViewModel.isSuccess.observe(viewLifecycleOwner, Observer {
            if (it) {
            }
        })

        courseViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }
        })

        courseViewModel.showProgressbar.observe(viewLifecycleOwner, Observer {
            if (!it) {
                mAddCourseProgressBar.visibility = View.GONE
            }
        })

    }


    fun Validate(): Boolean {
        if (mCourseNameEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course name", Toast.LENGTH_LONG).show()
            return false
        } else if (mCourseCodeEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course code", Toast.LENGTH_LONG).show()
            return false
        } else if (mCoursePlaceEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course place", Toast.LENGTH_LONG).show()
            return false
        } else return true


    }


    private fun setTimeField(mStartTimeEt: TextView) {
        timePickerDialog = TimePickerDialog(
            activity,
            { view, hourOfDay, minute ->
                calendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay + 1)
                calendar!!.set(Calendar.MINUTE, minute)
                mStartTimeEt.setText(timeFormatter.format(calendar!!.getTime()))
            },

            calendar!!.get(Calendar.HOUR_OF_DAY), calendar!!.get(Calendar.MINUTE), false
        )
        timePickerDialog!!.show()
    }

    private fun setDateTimeField() {
        datePickerDialog = DatePickerDialog(
            requireActivity(),
            { view, year, monthOfYear, dayOfMonth ->

                calendar!![year, monthOfYear] = dayOfMonth
                mCourseDateEt.setText(dateFormatter.format(calendar!!.time))

            },

            calendar!![Calendar.YEAR], calendar!![Calendar.MONTH],
            calendar!![Calendar.DAY_OF_MONTH]
        )
        datePickerDialog!!.show()
    }


}