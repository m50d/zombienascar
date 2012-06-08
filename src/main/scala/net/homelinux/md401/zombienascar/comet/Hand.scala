package net.homelinux.md401.zombienascar.comet
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck
import java.util.UUID
import net.liftweb.http.RenderOut
import net.homelinux.md401.zombienascar.backend.Card

case class NewHandMessage(cards: List[Card])

case class MoveMessage

class Hand extends CometActor with CometListener {
  var cards: List[Card] = List()
  
  def registerWith = Game
  
  override def lowPriority: PartialFunction[Any, Unit] = {
    case h: NewHandMessage => {cards = h.cards; reRender()}
  }  
  
  def render(): RenderOut = {
    val cards = Deck.hand(9)
    cards flatMap (c => {
      val id = UUID.randomUUID().toString()
      <img src={c.filename} id={id}></img><script>$(function() {{$("#{id}").draggable();}});</script>})
  }

}