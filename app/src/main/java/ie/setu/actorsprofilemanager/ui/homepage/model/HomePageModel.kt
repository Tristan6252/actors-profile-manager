package ie.setu.actorsprofilemanager.ui.homepage.model

import ie.setu.actorsprofilemanager.models.Actor
import java.time.LocalDate

class HomePageModel : HomePageModelInterface {



    var actor1 = Actor("Tom Cruise", 'M', LocalDate.parse("1962-08-03"), 6.1, false, "fsfsf1231sdfbb")
    var actor2 = Actor("Gary Oldman", 'M', LocalDate.parse("1958-03-21"), 5.4, false, "dfdfgdgdfg123")
    var actor3 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")

    var actor4 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor5 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor6 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor7 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor8 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor9 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor10 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor11 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor12 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor13 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor14 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor15 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")
    var actor16 = Actor("Alec Baldwin", 'M', LocalDate.parse("1958-04-03"), 6.9, true, "a4534534sdf")



    private var actors = arrayOf(actor1, actor2, actor3, actor4, actor5, actor6, actor7, actor8, actor9, actor10, actor11, actor12, actor13, actor14, actor15, actor16)


    override fun getActors(): Array<Actor> {
        return actors
    }


}