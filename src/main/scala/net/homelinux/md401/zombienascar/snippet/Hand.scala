package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck
import net.liftweb.http.js.JE.JsRaw
import java.util.UUID
import net.liftweb.http.SHtml
import net.liftweb.http.JsonHandler
import net.liftweb.http.js.JsCmd
import net.liftweb.util.JsonCmd
import net.liftweb.http.js.JE
import net.liftweb.http.js.JsCmds
import scala.xml.Text
import net.liftweb.http.js.JsonCall
import net.homelinux.md401.zombienascar.comet.MoveMessage
import net.homelinux.md401.zombienascar.comet.Game
import net.homelinux.md401.zombienascar.comet.RawMoveMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Json extends JsonHandler {
  val log = LoggerFactory.getLogger(getClass())
  def apply(in: Any): JsCmd = {
    in match {
      case JsonCmd("play", _, p: Map[String, String], _) => {
        log.info(p.toString())
        Game ! RawMoveMessage(p)
      }
      case _ => JsCmd.unitToJsCmd()
    }
  }
}

class playbutton {
  def render(): NodeSeq = {
    JsCmds.Script(Json.jsCmd) ++ <input type="submit" onclick={ Text(Json.call("play", JE.JsRaw("window.selected")).toJsCmd) }></input>
  }
}