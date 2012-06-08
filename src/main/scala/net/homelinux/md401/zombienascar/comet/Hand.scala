package net.homelinux.md401.zombienascar.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck
import java.util.UUID
import net.liftweb.http.RenderOut
import net.homelinux.md401.zombienascar.backend.Card

case class NewHandMessage(cards: List[Card])

case class MoveMessage(cards: List[Card])

case class RawMoveMessage(stringToId: Map[String, UUID])

class Hand extends CometActor with CometListener {
  var cards: Map[UUID, Card] = Map()

  def registerWith = Game

  override def lowPriority: PartialFunction[Any, Unit] = {
    case h: NewHandMessage => { cards = Map(h.cards map { c: Card => (UUID.randomUUID(), c) }: _*); reRender() }
    case rmm: RawMoveMessage => {  Game ! MoveMessage(List("card1", "card2", "card3", "card4", "card5") map {rmm.stringToId(_)} map {cards(_)})}
  }

  def render(): RenderOut = {
    cards.toList flatMap {
      case (id, c) => <img src={ c.filename } id={ id.toString }></img><script>$(function() {{$("#{ id }").draggable();}});</script>
    }
  }
}