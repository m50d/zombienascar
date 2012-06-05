package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck
import net.liftweb.http.js.JE.JsRaw
import java.util.UUID
import net.liftweb.http.SHtml

class hand {
  def render(): NodeSeq = {
    val cards = Deck.hand(9)
    cards flatMap (c => {
      val id = UUID.randomUUID().toString()
      <img src={c.filename} id={id}></img><script>$(function() {{$("#{id}").draggable();}});</script>})
  }
}