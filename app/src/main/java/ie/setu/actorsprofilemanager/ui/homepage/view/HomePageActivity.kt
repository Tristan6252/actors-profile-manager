package ie.setu.actorsprofilemanager.ui.homepage.view
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.databinding.ActivityHomePageBinding
import ie.setu.actorsprofilemanager.models.Actor
import ie.setu.actorsprofilemanager.ui.addactor.view.AddActorActivity
import ie.setu.actorsprofilemanager.ui.homepage.ScrollItemView
import ie.setu.actorsprofilemanager.ui.homepage.model.HomePageModel
import ie.setu.actorsprofilemanager.ui.homepage.model.MyClass
import ie.setu.actorsprofilemanager.ui.homepage.presenter.HomePagePresenter
import java.time.LocalDate
import java.time.Period
import dbmanager

class HomePageActivity : AppCompatActivity(), HomePageViewInterface {

    private var homepagePresenter : HomePagePresenter? = null

    private var actorProfileScrollView : ScrollView? = null

    private var actorProfileScrollViewLayout : LinearLayout? = null

    private lateinit var binding: ActivityHomePageBinding

    private lateinit  var adapter : ArrayAdapter<Actor>

    private var dbmanagerObject = dbmanager()

//    override fun onDestroy() {
//        super.onDestroy()
//        val gson = Gson()
//        val json = gson.toJson(MyClass.actors)
//        val sharedPref = getSharedPreferences("actors", Context.MODE_PRIVATE)
//        with(sharedPref.edit()) {
//            putString("actors", json)
//            apply()
//        }
//    }

    fun saveActors() {
        val gson = Gson()
        val json = gson.toJson(MyClass.actors)
        val sharedPref = getSharedPreferences("actors", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("actors", json)
            apply()
        }
    }


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
        adapter = ArrayAdapter(this, R.layout.activity_home_page, MyClass.actors)
            val sharedPref = getSharedPreferences("actors", Context.MODE_PRIVATE)
            val json = sharedPref.getString("actors", null)
            val gson = Gson()
//            MyClass.actors = gson.fromJson(json, Array<Actor>::class.java).toMutableList()
        //repopulateScrollView()
        dbmanagerObject.getActors(::repopulateScrollView)
    }

    interface deleteIndividualActorCallBackInterface {
        fun delete(actorName: String)
    }

    fun repopulateScrollView() {
        println("Actor array size: " + MyClass.actors.size)
        if(!MyClass.actors.isEmpty()) {
            for (item in MyClass.actors) {
                val currentDate = LocalDate.now()
                println("ITEM BIRTHDATE: " + item)
                val period = Period.between(LocalDate.parse(item.birthDate), currentDate)
                val years = period.years
                //    val actorName = TextView(this)
                //   actorName.text = item.name
                //  actorProfileScrollViewLayout?.addView(actorName)
                var thisScrollView = ScrollItemView(this)




                val callback = object : deleteIndividualActorCallBackInterface   {

                    override fun delete(actorName: String) {
                        dbmanagerObject.deleteIndividualActor(actorName)
                    }
                }

                thisScrollView.setOnDeleteIndividualActorPress(callback)
                thisScrollView.setOnTrashIconPress({
                    actorProfileScrollViewLayout?.removeAllViews()
                    repopulateScrollView()
                    actorProfileScrollView?.requestLayout()
                    saveActors()
                })
                thisScrollView.setActorName(item.name)
                thisScrollView.setActorAge(years) //64
                thisScrollView.setActorGender(item.gender)
                thisScrollView.setOnClickListener({
                    Log.d("actor name ", item.name)
                    homepagePresenter!!.onActorProfileListItemPress(item, this)
                })
                actorProfileScrollViewLayout?.addView(thisScrollView)

            }
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
           // R.id.nav_delete -> println("Delete selected!")
              R.id.nav_delete -> trashCanIconPressFeature()
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onActorProfilePress() {
        TODO("Not yet implemented")
    }

    override fun onAddActorProfileButtonPress() {
        TODO("Not yet implemented")
    }

    fun trashCanIconPressFeature() {
        // AlertDialog is a class that was imported above - remove if it doesn't work
        val alertMessage = AlertDialog.Builder(this)

        alertMessage.setMessage("Are you sure you wish to remove all actors?")
            .setPositiveButton("Confirm", DialogInterface.OnClickListener { dialog, id ->
                MyClass.actors.clear()
                dbmanagerObject.deleteAllActors {  }
               // adapter.notifyDataSetChanged()
                actorProfileScrollViewLayout?.removeAllViews()
                repopulateScrollView()
                actorProfileScrollView?.requestLayout()
                saveActors()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {dialog, id ->
                // Does nothing when they hit the cancel button!
            })

        alertMessage.create().show()

    }
}