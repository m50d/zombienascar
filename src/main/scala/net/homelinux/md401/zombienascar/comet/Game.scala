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
import net.homelinux.md401.zombienascar.backend.BlankSquare
import net.homelinux.md401.zombienascar.backend.EuclideanSquarePosition
import net.homelinux.md401.zombienascar.backend.EuclideanSquarePosition
import net.homelinux.md401.zombienascar.backend.DisplayTile
import net.homelinux.md401.zombienascar.backend.Square

//Will soon become an class, but for now it's an object
object Game extends LiftActor with ListenerManager {
	def createUpdate = NewHandMessage(Deck.hand(9))
  val allIndexes = List.tabulate(12, 12)(EuclideanSquarePosition(_, _))
  val allPositions = allIndexes.flatten
  val squares = Square.board(
      ".→..........",
      "............",
      "............",
      "............",
      "............",
      "............",
      "............",
      "............",
      "............",
      "............",
      "............",
      "............"
      )
  val board = allPositions map {p: EuclideanSquarePosition => (p, squares(p.x)(p.y))} toMap
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
      c = m.cards.foldLeft(c)({(d: Car, e: Card) =>
        val newPos = d.move(e)
        Game.board(newPos.position).actOn(newPos)
        })
        updateListeners()
    }
  }
} 

class DisplayCar extends CometActor with CometListener {
  var c: Car = Car(EuclideanSquarePosition(0, 0), North)
  
  def displayBoard = Game.board map {
    case (p, s) => (p, DisplayTile(s, if(c.position equals p) Some(c) else None))
  }
  
  def registerWith = PlayerCar
  def render: RenderOut = {
    val db = displayBoard
    <table>{
      Game.allIndexes map {
        row =>
          <tr>
            {
              row map {
                position => <td>{db(position).render}</td>
              }
            }
          </tr>
      }
    }</table><br /> ++
    Text("Car at " + c.position + " facing " + c.orientation)
  }
  override def lowPriority = {
    case nc: Car => c = nc; reRender()
  }
}