package ie.setu.actorsprofilemanager.ui.homepage.presenter

import ie.setu.actorsprofilemanager.models.Actor
import ie.setu.actorsprofilemanager.ui.homepage.model.HomePageModelInterface
import ie.setu.actorsprofilemanager.ui.homepage.view.HomePageViewInterface

class HomePagePresenter(private var homePageView : HomePageViewInterface, private val model : HomePageModelInterface) : HomePagePresenterInterface {
    override fun onFetchActorProfiles() {

    }

    override fun onDestroy() {

    }

    override fun loadInitData(): Array<Actor> {
        return model.getActors()
    }


}