package ie.setu.actorsprofilemanager.ui.profile

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.models.Actor
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.log
import com.google.android.gms.maps.MapsInitializer.Renderer;

class ActorProfileActivity : AppCompatActivity(), OnMapReadyCallback, OnMapsSdkInitializedCallback {

    private var actorName : TextView? = null
    private var actorGender : TextView? = null
    private var actorBirthDate : TextView? = null
    private var actorHeight : TextView? = null
    private var actorDeceasedOrNot : TextView? = null
    private var actorBirthPlaceGoogleMaps : TextView? = null
    private var profileImageView : ImageView? = null
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var actor: Actor




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actor_profile_activity)
        MapsInitializer.initialize(getApplicationContext(), Renderer.LATEST, this);
        val actor = intent.getSerializableExtra("actor") as? Actor
        if (actor != null) {
            this.actor = actor
        }
        println((actor.toString()))
        actorName = findViewById(R.id.actorName)
        actorName?.text = actor?.name

        actorGender = findViewById(R.id.gender)
        actorGender?.text = actor?.gender.toString()

        val today = LocalDate.now()
        val yearsBetween = ChronoUnit.YEARS.between(actor?.birthDate, today)
        actorBirthDate = findViewById(R.id.birthDate)
        actorBirthDate?.text = yearsBetween.toString()

        actorHeight = findViewById(R.id.height)
        actorHeight?.text = actor?.height.toString()

        actorDeceasedOrNot = findViewById(R.id.deceased)
        actorDeceasedOrNot?.text = actor?.deceasedOrNot.toString()

//        actorBirthPlaceGoogleMaps = findViewById(R.id.googlemaps)
//        actorBirthPlaceGoogleMaps?.text = actor?.birthPlaceGoogleMaps

        profileImageView = findViewById(R.id.actorProfileImageView)
        if (actor != null) {
            profileImageView?.setImageDrawable(resources.getDrawable(actor.actorProfileImage))
        }

        mapView = findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        val geoCoder = Geocoder(this)
        val cityName = actor.birthPlaceGoogleMaps
        val addresses = geoCoder.getFromLocationName(cityName, 1)
        if (addresses != null) {
            if(addresses.isNotEmpty()) {
                val address = addresses?.get(0)
                val latlng = address?.let { LatLng(it.latitude, address.longitude) }
                latlng?.let { MarkerOptions().position(it).title("Marker in $cityName") }
                    ?.let { this.googleMap.addMarker(it) }
                latlng?.let { CameraUpdateFactory.newLatLng(it) }?.let { this.googleMap.moveCamera(it) }
            }
        }
//        val sydney = LatLng(-34.0, 151.0)
//        this.googleMap.addMarker(MarkerOptions()
//            .position(sydney)
//            .title("Marker in Sydney"))
//        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onMapsSdkInitialized(p0: Renderer) {

    }
}