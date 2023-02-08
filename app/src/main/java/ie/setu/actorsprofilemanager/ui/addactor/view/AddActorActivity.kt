package ie.setu.actorsprofilemanager.ui.addactor.view

import android.app.ActionBar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ie.setu.actorsprofilemanager.R

class AddActorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_actor)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}