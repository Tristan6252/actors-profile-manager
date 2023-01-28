package ie.setu.actorsprofilemanager.models

import java.time.LocalDate


data class Actor(var name: String, var gender: Char, var birthDate: LocalDate, var height: Double, var deceasedOrNot: Boolean, var birthPlaceGoogleMaps: String) {



}