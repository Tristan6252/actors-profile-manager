package ie.setu.actorsprofilemanager.models

import android.graphics.Bitmap
import android.net.Uri
import java.io.Serializable
import java.time.LocalDate


data class Actor(var name: String, var gender: Char, var birthDate: String, var height: Double, var deceasedOrNot: Boolean, var birthPlaceGoogleMaps: String) :
    Serializable {



}