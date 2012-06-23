package net.homelinux.md401.zombienascar.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import net.homelinux.md401.zombienascar.backend.Deck
import net.homelinux.md401.zombienascar.backend.EuclideanSquareOrientation
import net.homelinux.md401.zombienascar.backend.EuclideanSquarePosition
import net.homelinux.md401.zombienascar.backend.Car
import net.homelinux.md401.zombienascar.backend.North
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import net.liftweb.http.RenderOut
import scala.xml.Text
import net.homelinux.md401.zombienascar.backend.Card

//Will soon become an class, but for now it's an object
object Game extends LiftActor with ListenerManager {
	def createUpdate = NewHandMessage(Deck.hand(9))
	override def lowPriority = {
	  case m: MoveMessage => {PlayerCar ! m; updateListeners()}
	  case m: RawMoveMessage => updateListeners(m)
	}
}

object PlayerCar extends LiftActor with ListenerManager {
  var c: Car = Car(EuclideanSquarePosition(0, 0), North)
  def createUpdate = c
  override def lowPriority = {
    case m: MoveMessage => {
      c = m.cards.foldLeft(c)((d: Car, e: Card) => d.move(e)); updateListeners()
    }
  }
} 

class DisplayCar extends CometActor with CometListener {
  var c: Car = Car(EuclideanSquarePosition(0, 0), North)
  
  def registerWith = PlayerCar
  def render: RenderOut = {
    Text("Car at " + c.position + " facing " + c.orientation)
  }
  override def lowPriority = {
    case nc: Car => c = nc; reRender()
  }
}