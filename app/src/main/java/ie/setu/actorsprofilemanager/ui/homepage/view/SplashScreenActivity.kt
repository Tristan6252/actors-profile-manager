package ie.setu.actorsprofilemanager.ui.homepage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ie.setu.actorsprofilemanager.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
            finish() // This stops the user going back to the Splashscreen on the Homepage if they hit the back button. Instead it'll bring the back to the Android Home screen
        }, 3000)
    }
}