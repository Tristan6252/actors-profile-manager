package ie.setu.actorsprofilemanager.ui.addactor.view

import android.app.ActionBar
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
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
import ie.setu.actorsprofilemanager.R
import java.util.*

class AddActorActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_GALLERY = 132


    private lateinit var actorName: EditText
    private lateinit var actorGender: EditText
    private lateinit var actorBD : EditText
    private lateinit var actorHeight: EditText
    private lateinit var actorGoogleMaps: EditText
    private lateinit var actorAddButton: Button
    private lateinit var actorDeceasedOrNot: CheckBox
    private lateinit var actorPicture: ImageView

   // private val Pick_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    private lateinit var getContent: ActivityResultLauncher<String>

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

                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    actorBD.setText(dat)
                },
                year, month, day
            )
            datePicker.show()
        }


//        actorPicture.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Select Image")
//            builder.setMessage("Choose your option?")
//            builder.setPositiveButton("Gallery")  { dialog : DialogInterface, which : Int ->
//                dialog.dismiss()
//
//                val intent = Intent(Intent.ACTION_PICK)
//                intent.type = "image/*"
//                startActivityForResult(intent, REQUEST_IMAGE_GALLERY)
//
//            }
//            builder.setNegativeButton("Camera")  { dialog : DialogInterface, which : Int ->
//                dialog.dismiss()
//            }
//            val dialog :AlertDialog = builder.create()
//            dialog.show()
//        }



        actorPicture.setOnClickListener {
            openImageChooser()
        }
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                imageUri = it
                actorPicture.setImageURI(imageUri)
            }
        }

        actorAddButton.setOnClickListener {
            validateFields()
        }




//        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent(),
//        ActivityResultCallback {
//            actorPicture.setImageURI(it)
//        })
//        actorPicture.setOnClickListener(View.OnClickListener {
//            loadImage.launch("image/*")
//        })




    }

    private fun openImageChooser() {
        getContent.launch("image/*")
    }



    private fun validateFields(): Boolean {
        val actorName = actorName.text.toString()
        val actorGender = actorGender.text.toString().toUpperCase().getOrNull(0)
        //actorBD -- turn this string into a LocalDate object removing the hyphens
        val actorHeight = actorHeight.text.toString()
        val actorGoogleMapsCity = actorGoogleMaps.text.toString()
        val actorDeceased = actorDeceasedOrNot.isChecked
        val actorImage = actorPicture.drawable


        if (actorName.isBlank()) {
            Toast.makeText(this, "Please enter an actor name", Toast.LENGTH_SHORT).show()
          //  R.id.actorName.requestFocus()
            return false
        }


        if (actorGender == null || (actorGender != 'M' && actorGender != 'F')) {
            Toast.makeText(this, "Please enter M or F for gender", Toast.LENGTH_SHORT).show()
           // genderEditText.requestFocus()
            return false
        }


        if (actorHeight.isBlank()) {
            Toast.makeText(this, "Please enter an actor height", Toast.LENGTH_SHORT).show()
         //   actorHeight.requestFocus()
            return false
        }

        val height: Double = try {
            actorHeight.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Please enter a valid height", Toast.LENGTH_SHORT).show()
         //   actorHeight.requestFocus()
            return false
        }

        if(actorGoogleMapsCity.isBlank()) {
            Toast.makeText(this, "Please enter an actor Google Maps City", Toast.LENGTH_SHORT).show()
            return false
        }

        if (actorImage == null) {
            Toast.makeText(this, "Please select an actor image", Toast.LENGTH_SHORT).show()
            return false
        }

        if (actorDeceased) {
            // actor is deceased
        } else {
            // actor is alive
        }

        return true
    }





//    private fun openFileChooser() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(intent, Pick_IMAGE_REQUEST)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == Pick_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
//            && data != null && data.data != null
//        ) {
//            imageUri = data.data
//            actorPicture.setImageURI(imageUri)
//        }
//    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(resultCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {
//            actorPicture.setImageURI(data.data)
//        } else {
//            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
//        }
//
//    }









//    fun pickPhoto(view: View) {
//        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
//        } else {
//            val phonePhotoGalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(phonePhotoGalleryIntent, 2)
//        }
//    }

//   override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//
//
//            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                val photoGalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                startActivityForResult(photoGalleryIntent, 2)
//            }
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//
//
//        if(resultCode == Activity.RESULT_OK && data != null) {
//            pickedPhoto = data.data!!
//            if(pickedPhoto != null) {
//                val source = ImageDecoder.createSource(this.contentResolver, pickedPhoto!!)
//                pickedBitMap = ImageDecoder.decodeBitmap(source)
//                actorPicture.setImageBitmap(pickedBitMap)
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data)
//    }



}