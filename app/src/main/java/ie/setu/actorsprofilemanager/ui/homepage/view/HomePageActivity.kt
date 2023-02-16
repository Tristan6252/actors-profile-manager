package ie.setu.actorsprofilemanager.ui.homepage.view
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.databinding.ActivityHomePageBinding
import ie.setu.actorsprofilemanager.databinding.ActorProfileActivityBinding
import ie.setu.actorsprofilemanager.ui.addactor.view.AddActorActivity
import ie.setu.actorsprofilemanager.ui.homepage.ScrollItemView
import ie.setu.actorsprofilemanager.ui.homepage.model.HomePageModel
import ie.setu.actorsprofilemanager.ui.homepage.model.MyClass
import ie.setu.actorsprofilemanager.ui.homepage.presenter.HomePagePresenter
import java.time.LocalDate
import java.time.Period

class HomePageActivity : AppCompatActivity(), HomePageViewInterface {

    private var homepagePresenter : HomePagePresenter? = null

    private var actorProfileScrollView : ScrollView? = null

    private var actorProfileScrollViewLayout : LinearLayout? = null

    private lateinit var binding: ActivityHomePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        homepagePresenter = HomePagePresenter(this, HomePageModel())
        actorProfileScrollView = findViewById(R.id.actorProfileScrollView)
        actorProfileScrollViewLayout = findViewById(R.id.actorProfileScrollViewLayout)

        actorProfileScrollView?.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                when(event?.action) {
//                    MotionEvent.ACTION_DOWN -> // Do something
//                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        for (item in MyClass.actors)  {
            val currentDate = LocalDate.now()
            val period = Period.between(item.birthDate, currentDate)
            val years = period.years
        //    val actorName = TextView(this)
         //   actorName.text = item.name
          //  actorProfileScrollViewLayout?.addView(actorName)
            var thisScrollView = ScrollItemView(this)
            thisScrollView.setActorName(item.name)
            thisScrollView.setActorAge(years) //64
            thisScrollView.setActorGender(item.gender)
            thisScrollView.setOnClickListener({
                Log.d("actor name " , item.name)
                homepagePresenter!!.onActorProfileListItemPress(item, this)
            })
            actorProfileScrollViewLayout?.addView(thisScrollView)

        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val intent = Intent(this, AddActorActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onActorProfilePress() {
        TODO("Not yet implemented")
    }

    override fun onAddActorProfileButtonPress() {
        TODO("Not yet implemented")
    }
}