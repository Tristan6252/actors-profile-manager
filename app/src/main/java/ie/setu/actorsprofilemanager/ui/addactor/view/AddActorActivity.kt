package ie.setu.actorsprofilemanager.ui.addactor.view

import android.app.ActionBar
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import ie.setu.actorsprofilemanager.R
import java.util.*

class AddActorActivity : AppCompatActivity() {

    private lateinit var actorName: EditText
    private lateinit var actorGender: EditText
    private lateinit var actorBD : EditText
    private lateinit var actorHeight: EditText
    private lateinit var actorAddButton: Button
    private lateinit var actorDeceasedOrNot: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actor)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        actorName = findViewById(R.id.actorName1)
        actorGender = findViewById(R.id.actorGender1)
        actorBD = findViewById(R.id.actorBirthDate1)
        actorHeight = findViewById(R.id.actorHeight1)
        actorDeceasedOrNot = findViewById(R.id.actorDeceasedOrNot1)
        actorAddButton = findViewById(R.id.addActorButton)


        actorBD.setOnClickListener {

            val calendarInstance = Calendar.getInstance()

            val year = calendarInstance.get(Calendar.YEAR)
            val month = calendarInstance.get(Calendar.MONTH)
            var day = calendarInstance.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->

                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    actorBD.setText(dat)
                },
                year, month, day
            )
            datePicker.show()
        }


    }
}