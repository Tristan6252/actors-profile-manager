package ie.setu.actorsprofilemanager.ui.homepage.model

import ie.setu.actorsprofilemanager.models.Actor

interface HomePageModelInterface {

    fun getActors() : Array<Actor>

}