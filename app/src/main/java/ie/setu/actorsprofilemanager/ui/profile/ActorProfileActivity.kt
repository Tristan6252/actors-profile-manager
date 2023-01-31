package ie.setu.actorsprofilemanager.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.models.Actor
import org.w3c.dom.Text
import java.time.LocalDate
import kotlin.math.log

class ActorProfileActivity : AppCompatActivity() {

    private var actorName : TextView? = null
    private var actorGender : TextView? = null
    private var actorBirthDate : TextView? = null
    private var actorHeight : TextView? = null
    private var actorDeceasedOrNot : TextView? = null
    private var actorBirthPlaceGoogleMaps : TextView? = null
    private var profileImageView : ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actor_profile_activity)
        val actor = intent.getSerializableExtra("actor") as? Actor
        println((actor.toString()))
        actorName = findViewById(R.id.actorName)
        actorName?.text = actor?.name

        actorGender = findViewById(R.id.gender)
        actorGender?.text = actor?.gender.toString()

        actorBirthDate = findViewById(R.id.birthDate)
        actorBirthDate?.text = actor?.birthDate.toString()

        actorHeight = findViewById(R.id.height)
        actorHeight?.text = actor?.height.toString()

        actorDeceasedOrNot = findViewById(R.id.deceased)
        actorDeceasedOrNot?.text = actor?.deceasedOrNot.toString()

        actorBirthPlaceGoogleMaps = findViewById(R.id.googlemaps)
        actorBirthPlaceGoogleMaps?.text = actor?.birthPlaceGoogleMaps

        profileImageView = findViewById(R.id.actorProfileImageView)
        if (actor != null) {
            profileImageView?.setImageDrawable(resources.getDrawable(actor.actorProfileImage))
        }
    }
}