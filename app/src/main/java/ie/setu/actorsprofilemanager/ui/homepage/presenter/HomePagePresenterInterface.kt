package ie.setu.actorsprofilemanager.ui.homepage.presenter

import android.content.Context
import ie.setu.actorsprofilemanager.models.Actor

interface HomePagePresenterInterface {

    fun onFetchActorProfiles()

    fun onDestroy()

    fun loadInitData() : MutableList<Actor>

    fun onActorProfileListItemPress(actor: Actor, context: Context)


}