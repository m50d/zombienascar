package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck

class hand {
  def render(): NodeSeq = {
    val cards = Deck.hand(9)
    cards map (c => <img src={c.filename}></img>)
  }
}