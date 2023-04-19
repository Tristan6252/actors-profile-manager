import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import ie.setu.actorsprofilemanager.models.Actor
import ie.setu.actorsprofilemanager.ui.homepage.model.MyClass

class dbmanager {
    val database = FirebaseDatabase.getInstance("https://actors-profile-manager-default-rtdb.europe-west1.firebasedatabase.app/").reference

    fun getActors(callback: () -> Unit) {
        MyClass.actors.clear()
        val actorRef = database.child("actors")

        actorRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                MyClass.actors.clear()
                println("This is the snapshot: " + snapshot)

               // println("This is the post print statement: " + post)
               for(actorSnapshot in snapshot.children) {
                    val actorAsAStringObject = actorSnapshot.getValue<Actor>()
                   if (actorAsAStringObject != null) {
                       MyClass.actors.add(actorAsAStringObject)
                   }
                    println("Actor snapshot: " + actorSnapshot)
               }
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun addActor(actor1: Actor) {
        val key = database.child(actor1.name).push().key
        if (key != null) {
            println("Database key: " + key)
            database.child("actors").child(key).setValue(actor1).addOnSuccessListener {println("success")} .addOnFailureListener { println("failure") }
        }
    }

    fun deleteAllActors(callback: () -> Unit) {
        database.child("actors").removeValue()
            .addOnSuccessListener {
                MyClass.actors.clear()
                callback()
            }
            .addOnFailureListener { exception ->

            }
    }

    fun deleteIndividualActor(actorName: String, callback: () -> Unit) {
        val actorRef = database.child("actors").orderByChild("name").equalTo(actorName)

        actorRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (actorSnapshot in snapshot.children) {
                    actorSnapshot.ref.removeValue()
                }
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                callback()
            }
        })


    }

    fun updateIndividualActor(newActor: Actor, actorName: String) {
        val userQuery = database.child("actors").orderByChild("name").equalTo(actorName)

        userQuery.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(actorSnapshot in snapshot.children) {
                    val thisActor = actorSnapshot.getValue(Actor::class.java)
                    if(thisActor != null && thisActor.name == actorName) {
                        val userRef = actorSnapshot.key?.let { database.child("actors").child(it).setValue(newActor) }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
