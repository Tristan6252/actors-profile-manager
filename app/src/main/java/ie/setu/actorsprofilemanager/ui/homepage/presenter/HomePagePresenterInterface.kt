package ie.setu.actorsprofilemanager.ui.homepage.presenter

import ie.setu.actorsprofilemanager.models.Actor

interface HomePagePresenterInterface {

    fun onFetchActorProfiles()

    fun onDestroy()

    fun loadInitData() : Array<Actor>


}