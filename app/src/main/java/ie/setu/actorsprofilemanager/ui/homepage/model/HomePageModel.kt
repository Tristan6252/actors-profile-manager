package ie.setu.actorsprofilemanager.ui.homepage.model

import ie.setu.actorsprofilemanager.R
import ie.setu.actorsprofilemanager.models.Actor
import ie.setu.actorsprofilemanager.ui.homepage.model.MyClass.Companion.actors
import java.time.LocalDate


class MyClass {

 //   object actor1 = Actor("Tom Cruise", 'M', LocalDate.parse("1962-08-03"), 6.1, false, "Los Angeles", R.drawable.tomcruise)
//    var actor2 = Actor("Gary Oldman", 'M', LocalDate.parse("1958-03-21"), 5.4, false, "London", R.drawable.gary_oldman)
//    var actor3 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//
//    var actor4 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor5 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor6 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor7 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor8 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor9 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor10 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor11 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor12 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor13 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor14 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor15 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor16 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)

    companion object {
        @JvmStatic
        var actors: MutableList<Actor> = mutableListOf<Actor>()
    }


}
class HomePageModel : HomePageModelInterface {



//    var actor1 = Actor("Tom Cruise", 'M', LocalDate.parse("1962-08-03"), 6.1, false, "Los Angeles", R.drawable.tomcruise)
//    var actor2 = Actor("Gary Oldman", 'M', LocalDate.parse("1958-03-21"), 5.4, false, "London", R.drawable.gary_oldman)
//    var actor3 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//
//    var actor4 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor5 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor6 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor7 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor8 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor9 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor10 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor11 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor12 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor13 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor14 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor15 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)
//    var actor16 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "New York", R.drawable.alecbaldwin)



   // private var actors = arrayOf(actor1, actor2, actor3, actor4, actor5, actor6, actor7, actor8, actor9, actor10, actor11, actor12, actor13, actor14, actor15, actor16)

  //  private var actors = mutableListOf<Actor>(actor1, actor2, actor3, actor4, actor5, actor6, actor7, actor8, actor9, actor10, actor11, actor12, actor13, actor14, actor15, actor16)



    override fun getActors(): MutableList<Actor> {
        return actors
    }

    override fun addActor(actor: Actor) {
        actors.add(actor)
    }


}