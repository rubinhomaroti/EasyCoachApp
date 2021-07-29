package br.com.fiap.easycoachapp.ui.extensions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class TextViewExtensions {
    companion object {
        fun EditText.transformIntoDatePicker(context: Context) {
            isFocusableInTouchMode = false
            isClickable = true
            isFocusable = false

            val myCalendar = Calendar.getInstance()
            val datePickerOnDataSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                }

            val timePickerOnTimeSetListener =
                TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    myCalendar.set(Calendar.HOUR, hour)
                    myCalendar.set(Calendar.MINUTE, minute)

                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    val dateStr = sdf.format(myCalendar.time)

                    var hourStr = hour.toString()
                    var minuteStr = minute.toString()

                    if (hour == 0)
                        hourStr = "00"

                    if (minute == 0)
                        minuteStr = "00"

                    setText("$dateStr $hourStr:$minuteStr")
                }

            setOnClickListener {
                var timeDialog = TimePickerDialog(
                    context,
                    timePickerOnTimeSetListener,
                    myCalendar.get(Calendar.HOUR),
                    myCalendar.get(Calendar.MINUTE),
                    true
                )

                timeDialog.run {
                    show()
                }

                var dateDialog = DatePickerDialog(
                    context, datePickerOnDataSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                )

                dateDialog.setOnCancelListener { timeDialog.cancel() }

                dateDialog.run {
                    datePicker.minDate = Date().time
                    show()
                }
            }
        }
    }
}