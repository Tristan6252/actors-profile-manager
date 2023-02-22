package ie.setu.actorsprofilemanager

import ie.setu.actorsprofilemanager.models.Actor
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Ignore
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testActorFields() {
        val actor = Actor("Tom Hanks", 'M', "1956-07-09", 1.83, false, "https://www.google.com/maps/place/Concord,+California")
        assertEquals("Tom Hanks", actor.name)
        assertEquals('M', actor.gender)
        assertEquals("1956-07-09", actor.birthDate)
        assertEquals(1.83, actor.height, 0.01)
        assertFalse(actor.deceasedOrNot)
        assertEquals("https://www.google.com/maps/place/Concord,+California", actor.birthPlaceGoogleMaps)
    }

    @Test
    fun testActorEquality() {
        val actor1 = Actor("Emma Stone", 'F', "1988-11-06", 1.68, false, "https://www.google.com/maps/place/Scottsdale,+Arizona")
        val actor2 = Actor("Emma Stone", 'F', "1988-11-06", 1.68, false, "https://www.google.com/maps/place/Scottsdale,+Arizona")
        val actor3 = Actor("Ryan Gosling", 'M', "1980-11-12", 1.84, false, "https://www.google.com/maps/place/London,+Ontario")
        assertEquals(actor1, actor2)
        assertNotEquals(actor1, actor3)
    }



}