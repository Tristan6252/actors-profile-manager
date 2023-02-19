package ie.setu.actorsprofilemanager.ui.homepage

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import ie.setu.actorsprofilemanager.R

class ScrollItemView @JvmOverloads constructor(contextOfTypeContext: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(contextOfTypeContext, attrs, defStyle) {

    private var actorName: TextView? = null
    private var actorAge: TextView? = null
    private var actorGender: TextView? = null

    init {
        inflate(context, R.layout.card_actor, this)
        actorName = findViewById(R.id.actorNameCard)
        actorAge = findViewById(R.id.actorAgeCard)
        actorGender = findViewById(R.id.actorGenderCard)
    }

    fun setActorName(actorName: String) {
        this.actorName?.text = actorName
    }

    fun setActorAge(actorAge: Int) {
        this.actorAge?.text = actorAge.toString()
    }

    fun setActorGender(actorGender: Char) {
        this.actorGender?.text = actorGender.toString()
    }


}