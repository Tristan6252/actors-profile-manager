package ie.setu.actorsprofilemanager.ui.homepage.view

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import dbmanager
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.models.Actor
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.*

class EditActorActivity : AppCompatActivity() {

    private lateinit var currentActorName: String
    private lateinit var editActorName: EditText
    private lateinit var editActorGender: EditText
    private lateinit var editActorBD: EditText
    private lateinit var editActorHeight: EditText
    private lateinit var editActorGoogleMaps: EditText
    private lateinit var editActorUpdateButton: Button
    private lateinit var editActorDeceasedOrNot: CheckBox
    private lateinit var editActorPicture: ImageView
    private lateinit var editActorPictureBitMap: Bitmap

    private lateinit var actorBirthDate: LocalDate

    private var dbmanagerObject = dbmanager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_actor)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        editActorName = findViewById(R.id.editActorName1)
        editActorGender = findViewById(R.id.editActorGender1)
        editActorBD = findViewById(R.id.editActorBirthDate1)
        editActorHeight = findViewById(R.id.editActorHeight1)
        editActorDeceasedOrNot = findViewById(R.id.editActorDeceasedOrNot1)
        editActorGoogleMaps = findViewById(R.id.editActorGoogleMapsString)

        editActorPicture = findViewById(R.id.editActorProfilePicture)

        editActorUpdateButton = findViewById(R.id.editActorButton)

       val currentActor: Actor = intent.getSerializableExtra("actor") as Actor
        currentActorName = currentActor.name
        editActorName.setText(currentActor.name)
        editActorGender.setText(currentActor.gender)
        editActorBD.setText(currentActor.birthDate)
        actorBirthDate = LocalDate.parse(currentActor.birthDate)
        editActorHeight.setText(currentActor.height.toString())
        editActorDeceasedOrNot.setChecked(currentActor.deceasedOrNot)
        editActorGoogleMaps.setText(currentActor.birthPlaceGoogleMaps)

        val actorBase64ImageString =  currentActor?.image
        val decodedStringBytes = android.util.Base64.decode(actorBase64ImageString, android.util.Base64.DEFAULT)
        val bitmapOfImage = BitmapFactory.decodeByteArray(decodedStringBytes, 0, decodedStringBytes.size)
        editActorPicture?.setImageBitmap(bitmapOfImage)

        editActorPictureBitMap = bitmapOfImage

        editActorBD.setOnClickListener {

            val calendarInstance = Calendar.getInstance()

            val year = calendarInstance.get(Calendar.YEAR)
            val month = calendarInstance.get(Calendar.MONTH)
            var day = calendarInstance.get(Calendar.DAY_OF_MONTH)


            val datePicker = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->

                    actorBirthDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
                    editActorBD.setText(actorBirthDate.toString())
                },
                year, month, day
            )
            datePicker.show()


        }


        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    editActorPictureBitMap = getBitmapFromUri(uri)
                    editActorPicture.setImageURI(uri)
                }
            }

        editActorPicture.setOnClickListener {
            getContent.launch("image/*")
        }



        editActorUpdateButton.setOnClickListener {
            validateFields()

        }
    }

    private fun validateFields(): Boolean {
        val actorName = editActorName.text.toString()
        val actorGender = editActorGender.text.toString().toUpperCase().getOrNull(0)
        val actorBD = editActorBD.text.toString()
        val actorHeight = editActorHeight.text.toString()
        val actorGoogleMapsCity = editActorGoogleMaps.text.toString()
        val actorDeceased = editActorDeceasedOrNot.isChecked
        val actorImage = editActorPicture.drawable


        if (actorName.isBlank()) {
            Toast.makeText(this, "Please enter an actor name", Toast.LENGTH_SHORT).show()
            this.editActorName.requestFocus()
            return false
        }


        if (actorGender == null || (actorGender != 'M' && actorGender != 'F')) {
            Toast.makeText(this, "Please enter M or F for gender", Toast.LENGTH_SHORT).show()
            this.editActorGender.requestFocus()
            return false
        }

        if (actorBD == null || actorBD.isBlank()) {
            Toast.makeText(this, "Please enter Actor birth date", Toast.LENGTH_SHORT).show()
            this.editActorBD.requestFocus()
            return false
        }


        if (actorHeight.isBlank()) {
            Toast.makeText(this, "Please enter an actor height", Toast.LENGTH_SHORT).show()
            this.editActorHeight.requestFocus()
            return false
        }

        val height: Double = try {
            actorHeight.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid height", Toast.LENGTH_SHORT).show()
            this.editActorHeight.requestFocus()
            return false
        }

        if (actorGoogleMapsCity.isBlank()) {
            Toast.makeText(this, "Please enter an actor Google Maps City", Toast.LENGTH_SHORT)
                .show()
            this.editActorGoogleMaps.requestFocus()
            return false
        }

        if (actorImage == null) {
            Toast.makeText(this, "Please choose an actor profile picture", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        val baos = ByteArrayOutputStream()
        println("editActorPicBitmap: " + editActorPictureBitMap)
        editActorPictureBitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        println(Base64.getEncoder().encodeToString(baos.toByteArray()))
        val actor1 = Actor(actorName, actorGender.toString(), actorBirthDate.toString(), height, actorDeceased, actorGoogleMapsCity, Base64.getEncoder().encodeToString(baos.toByteArray()))

        dbmanagerObject.updateIndividualActor(actor1, currentActorName)


        return true
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }
}