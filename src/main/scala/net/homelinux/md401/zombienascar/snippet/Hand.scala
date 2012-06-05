package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck
import net.liftweb.http.js.JE.JsRaw
import java.util.UUID
import net.liftweb.http.SHtml
import net.liftweb.http.JsonHandler
import net.liftweb.http.js.JsCmd
import net.liftweb.util.JsonCmd

class hand {
  def render(): NodeSeq = {
    val cards = Deck.hand(9)
    cards flatMap (c => {
      val id = UUID.randomUUID().toString()
      <img src={c.filename} id={id}></img><script>$(function() {{$("#{id}").draggable();}});</script>})
  }
}

object json extends JsonHandler {
  def apply(in: Any): JsCmd = { in match { 
            case JsonCmd("processForm", _, p: Map[String, _], _) => {
                // process the form or whatever 
//                println("Cars = " + urlDecode(p("cars"))) 
//                println("Name = " + urlDecode(p("name"))) 
//                <b>{p}</b> 
            }
            case _ => JsCmd.unitToJsCmd()
        }}
}