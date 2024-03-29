package ie.setu.actorsprofilemanager.ui.homepage

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import dbmanager
import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.ui.addactor.view.AddActorActivity
import ie.setu.actorsprofilemanager.ui.homepage.model.MyClass
import ie.setu.actorsprofilemanager.ui.homepage.view.EditActorActivity
import ie.setu.actorsprofilemanager.ui.homepage.view.HomePageActivity
import java.lang.reflect.Executable

class ScrollItemView @JvmOverloads constructor(contextOfTypeContext: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(contextOfTypeContext, attrs, defStyle) {

    private var actorName: TextView? = null
    private var actorAge: TextView? = null
    private var actorGender: TextView? = null
    private var actorDeleteIcon: ImageView? = null

    private var actorEditIcon: ImageView? = null

    private lateinit var trashPress: () -> Unit?

    private var dbmanagerObject = dbmanager()

    private lateinit var deleteIndividualActorPress : HomePageActivity.deleteIndividualActorCallBackInterface
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        actorEditIcon?.setOnClickListener {
            val intent = Intent(context, EditActorActivity::class.java)
            val actorToEdit = MyClass.actors.find{it.name == actorName?.text}
            intent.putExtra("actor", actorToEdit)
            context.startActivity(intent)
        }
    }

    init {
        inflate(context, R.layout.card_actor, this)
        actorName = findViewById(R.id.actorNameCard)
        actorAge = findViewById(R.id.actorAgeCard)
        actorGender = findViewById(R.id.actorGenderCard)
        actorDeleteIcon = findViewById(R.id.deleteIndividualActorIcon)

        actorEditIcon = findViewById(R.id.editIndividualActorIcon)
//        actorEditIcon?.setOnClickListener {
//            val intent = Intent(context, EditActorActivity::class.java)
//            context.startActivity(intent)
//        }

        actorDeleteIcon?.setOnClickListener {
            val alertMessage = AlertDialog.Builder(context)
            alertMessage.setTitle("Delete Actor")
            alertMessage.setMessage("Are you sure you want to delete this actor?")
            alertMessage.setPositiveButton("Confirm") {_, _ ->
                println("Actor's array size before delete individual actor: " + MyClass.actors.size)
                val actorToDelete = MyClass.actors.find{it.name == actorName?.text}
                MyClass.actors.remove(actorToDelete)
                println("Actor's array size after delete individual actor: " + MyClass.actors.size)
                println("Actor Name: " + actorName)
                println(actorToDelete)
            //    dbmanagerObject.deleteIndividualActor(actorName.toString(), ::repopulateScrollView)
                deleteIndividualActorPress.delete(this.actorName?.text.toString(), {
                    trashPress()
                })

            }
            alertMessage.setNegativeButton("Cancel") { _, _ -> }
            val dialog = alertMessage.create()
            dialog.show()
        }
    }

    fun setActorName(actorName: String) {
        this.actorName?.text = actorName
    }

    fun setActorAge(actorAge: Int) {
        this.actorAge?.text = actorAge.toString()
    }

    fun setActorGender(actorGender: String) {
        this.actorGender?.text = actorGender.toString()
    }

    fun setOnTrashIconPress(trashPress:() -> Unit) {
        this.trashPress = trashPress
    }

//    interface deleteIndividualActorCallBackInterface {
//        fun delete(actorName: String)
//    }

    fun setOnDeleteIndividualActorPress(callback: HomePageActivity.deleteIndividualActorCallBackInterface) {
        //callback.delete(this.actorName?.text.toString())
        this.deleteIndividualActorPress = callback
    }


}