package net.homelinux.md401.zombienascar.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import net.homelinux.md401.zombienascar.backend.Deck
import net.homelinux.md401.zombienascar.backend.EuclideanSquareOrientation
import net.homelinux.md401.zombienascar.backend.EuclideanSquarePosition
import net.homelinux.md401.zombienascar.backend.Car
import net.homelinux.md401.zombienascar.backend.North

//Will soon become an class, but for now it's an object
object Game extends LiftActor with ListenerManager {
	def createUpdate = NewHandMessage(Deck.hand(9))
	override def lowPriority = {
	  case m: MoveMessage => {PlayerCar ! m; updateListeners()}
	}
}

object PlayerCar extends LiftActor with ListenerManager {
  var c: Car = Car(EuclideanSquarePosition(0, 0), North())
  def createUpdate = c
} 