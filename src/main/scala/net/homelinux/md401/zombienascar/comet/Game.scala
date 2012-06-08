package net.homelinux.md401.zombienascar.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import net.homelinux.md401.zombienascar.backend.Deck

//Will soon become an class, but for now it's an object
object Game extends LiftActor with ListenerManager {
	def createUpdate = NewHandMessage(Deck.hand(9))
	override def lowPriority = {
	  case m: MoveMessage => updateListeners()
	}
}