package com.Fcih.attendance_admin.Desgin.Fragments.Courses

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.Fcih.attendance_admin.Data.CourseList.Course
import com.Fcih.attendance_admin.Data.CourseList.EditCourseViewModel
import com.Fcih.attendance_admin.R
import kotlinx.android.synthetic.main.fragment_edit_course.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class EditCourseFragment : Fragment() {


    lateinit var courseGroup: String
    lateinit var editCourseViewModel: EditCourseViewModel
    private var datePickerDialog: DatePickerDialog? = null
    private var timePickerDialog: TimePickerDialog? = null
    private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.US)
    var calendar: Calendar? = null
    private val dateFormatter = SimpleDateFormat("E", Locale.US)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editCourseViewModel = ViewModelProvider(this).get(EditCourseViewModel::class.java)
        calendar = Calendar.getInstance()
        mGroupsp.isEnabled = false
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

        mSaveCourseBtn.setOnClickListener {
            var courseName = mCourseNameEt.text.toString()
            var courseCode = mCourseCodeEt.text.toString()
            var courseDate = mCourseDateEt.text.toString()
            var courseStartTime = mStartTimeEt.text.toString()
            var courseEndTime = mEndTimeEt.text.toString()
            var coursePlace = mCoursePlaceEt.text.toString()

            if (Validate()) {
                mEditCourseProgressBar.visibility = View.VISIBLE
                lifecycleScope.launch(Dispatchers.IO) {
                    editCourseViewModel.editCourse(
                        Course(
                            courseCode,
                            courseName,
                            courseDate,
                            courseStartTime,
                            courseEndTime,
                            coursePlace,
                            courseGroup,
                            null
                        )
                    )

                }

            }

        }

        observers()

        mStartTimeEt.setOnClickListener {
            setTimeField(mStartTimeEt)
        }
        mEndTimeEt.setOnClickListener {
            setTimeField(mEndTimeEt)
        }
        mCourseDateEt.setOnClickListener {
            setDateTimeField()
        }


        var data = EditCourseFragmentArgs.fromBundle(requireArguments()).courseData
        mCourseNameEt.setText(data.courseName)
        mCourseCodeEt.setText(data.courseCode)
        mCourseDateEt.setText(data.courseDate)
        mStartTimeEt.setText(data.courseTimeFrom)
        mEndTimeEt.setText(data.courseTimeTo)
        mCoursePlaceEt.setText(data.coursePlace)
    }

    private fun observers() {
//        editCourseViewModel.isSuccess.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            if (it) {
//                findNavController().navigate(EditCourseFragmentDirections.actionEditCourseFragmentToCourseListFragment())
//                editCourseViewModel.doneNavigate()
//            }
//        })
        editCourseViewModel.error.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.isNotEmpty()) {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            }
        })

        editCourseViewModel.showProgressbar.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                if (!it) {
                    mEditCourseProgressBar.visibility = View.GONE
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
        } else if (mCourseDateEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course code", Toast.LENGTH_LONG).show()
            return false
        } else if (mStartTimeEt.text.toString().isEmpty()) {
            Toast.makeText(activity, "Please enter course code", Toast.LENGTH_LONG).show()
            return false
        } else if (mEndTimeEt.text.toString().isEmpty()) {
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
                calendar!!.set(Calendar.HOUR_OF_DAY, hourOfDay)
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