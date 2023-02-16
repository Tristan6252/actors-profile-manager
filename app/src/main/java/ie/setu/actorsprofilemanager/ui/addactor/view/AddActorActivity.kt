package ie.setu.actorsprofilemanager.ui.addactor.view

import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.models.Actor
import ie.setu.actorsprofilemanager.ui.homepage.model.HomePageModel
import ie.setu.actorsprofilemanager.ui.homepage.model.MyClass
import ie.setu.actorsprofilemanager.ui.homepage.view.HomePageActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddActorActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_GALLERY = 132
 //   private val homePageModelObject =

  //  private val homePageActivityObject = HomePageActivity()


    private lateinit var actorName: EditText
    private lateinit var actorGender: EditText
    private lateinit var actorBD : EditText
    private lateinit var actorHeight: EditText
    private lateinit var actorGoogleMaps: EditText
    private lateinit var actorAddButton: Button
    private lateinit var actorDeceasedOrNot: CheckBox
    private lateinit var actorPicture: ImageView
//    private lateinit var bitmap : Bitmap
//    private lateinit var actorProfileImageUri : Uri



    private lateinit var actorBirthDate : LocalDate

    // private val Pick_IMAGE_REQUEST = 1
  //  private var imageUri: Uri? = null
   // private lateinit var getContent: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actor)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        actorName = findViewById(R.id.actorName1)
        actorGender = findViewById(R.id.actorGender1)
        actorBD = findViewById(R.id.actorBirthDate1)
        actorHeight = findViewById(R.id.actorHeight1)
        actorDeceasedOrNot = findViewById(R.id.actorDeceasedOrNot1)
        actorGoogleMaps = findViewById(R.id.actorGoogleMapsString)

        actorPicture = findViewById(R.id.actorProfilePicture)

        actorAddButton = findViewById(R.id.addActorButton)


        actorBD.setOnClickListener {

            val calendarInstance = Calendar.getInstance()

            val year = calendarInstance.get(Calendar.YEAR)
            val month = calendarInstance.get(Calendar.MONTH)
            var day = calendarInstance.get(Calendar.DAY_OF_MONTH)


            val datePicker = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->

                    val formattedDate = "$dayOfMonth-${monthOfYear + 1}-$year"
                    val formatter = DateTimeFormatter.ofPattern("d-M-yyyy")
                     actorBirthDate = LocalDate.parse(formattedDate, formatter)
                    actorBD.setText(formattedDate)
                },
                year, month, day
            )
            datePicker.show()




        }

        actorAddButton.setOnClickListener {
            validateFields()

        }

    }





    private fun validateFields(): Boolean {
        val actorName = actorName.text.toString()
        val actorGender = actorGender.text.toString().toUpperCase().getOrNull(0)
        val actorBD = actorBD.text.toString()
        val actorHeight = actorHeight.text.toString()
        val actorGoogleMapsCity = actorGoogleMaps.text.toString()
        val actorDeceased = actorDeceasedOrNot.isChecked
    //    val actorImage = actorPicture.drawable


        if (actorName.isBlank()) {
            Toast.makeText(this, "Please enter an actor name", Toast.LENGTH_SHORT).show()
            this.actorName.requestFocus()
            return false
        }


        if (actorGender == null || (actorGender != 'M' && actorGender != 'F')) {
            Toast.makeText(this, "Please enter M or F for gender", Toast.LENGTH_SHORT).show()
            this.actorGender.requestFocus()
            return false
        }

        if(actorBD == null || actorBD.isBlank()) {
            Toast.makeText(this, "Please enter Actor birth date", Toast.LENGTH_SHORT).show()
            this.actorBD.requestFocus()
            return false
        }


        if (actorHeight.isBlank()) {
            Toast.makeText(this, "Please enter an actor height", Toast.LENGTH_SHORT).show()
            this.actorHeight.requestFocus()
            return false
        }

        val height: Double = try {
            actorHeight.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid height", Toast.LENGTH_SHORT).show()
            this.actorHeight.requestFocus()
            return false
        }

        if(actorGoogleMapsCity.isBlank()) {
            Toast.makeText(this, "Please enter an actor Google Maps City", Toast.LENGTH_SHORT).show()
            this.actorGoogleMaps.requestFocus()
            return false
        }




        val actor1 = Actor(actorName, actorGender, actorBirthDate, height, actorDeceased, actorGoogleMapsCity)
        MyClass.actors.add(actor1)
        Toast.makeText(this, "$actorName has been successfully added!", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }







}