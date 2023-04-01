package udemy.course.ageInMinApp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var dateText: TextView? = null
    private var minutesText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dobBtn: Button = findViewById(R.id.dob_btn)

        dobBtn.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker() {
        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _, yearPicked, monthPicked, dayOfMonthPicked ->
                val datePicked = "$dayOfMonthPicked/${monthPicked+1}/$yearPicked"

                dateText = findViewById(R.id.date_text)
                dateText?.text = datePicked

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.GERMANY)
                val datePickedParsed = sdf.parse(datePicked)
                datePickedParsed?.let {dp ->
                    val dppMin = dp.time / 60000L
                    val currentSystemTime = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentSystemTime?.let {
                        val currentMin = it.time / 60000L
                        val diffMin = currentMin-dppMin
                        dateText = findViewById(R.id.minutes_text)
                        dateText?.text = diffMin.toString()
                    }

                }
            }, year, month, day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000L //milliseconds in 1 day
        datePickerDialog.show()
    }
}