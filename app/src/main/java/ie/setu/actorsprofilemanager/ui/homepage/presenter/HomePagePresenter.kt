package ie.setu.actorsprofilemanager.ui.homepage.presenter

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import ie.setu.actorsprofilemanager.models.Actor
import ie.setu.actorsprofilemanager.ui.homepage.model.HomePageModelInterface
import ie.setu.actorsprofilemanager.ui.homepage.view.HomePageViewInterface
import ie.setu.actorsprofilemanager.ui.profile.ActorProfileActivity

class HomePagePresenter(private var homePageView : HomePageViewInterface, private val model : HomePageModelInterface) : HomePagePresenterInterface {
    override fun onFetchActorProfiles() {

    }

    override fun onDestroy() {

    }

    override fun loadInitData(): MutableList<Actor> {
        return model.getActors()
    }

    override fun onActorProfileListItemPress(actor: Actor, context: Context) {
        val intent = Intent(context, ActorProfileActivity::class.java)
        intent.putExtra("actor", actor)
       // startActivity(intent)
     context.startActivity(intent)
    }


}